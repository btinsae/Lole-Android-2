package fanos.com.lole.providers;

import android.app.SearchManager;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class SearchContentProvider extends ContentProvider {


    private static final String ITEMS = "item/" + SearchManager.SUGGEST_URI_PATH_QUERY + "/*";

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI("fanos.com.lole.provider", ITEMS, 1);
    }


    private static String[] matrixCursorColumns = {"_id",
            SearchManager.SUGGEST_COLUMN_TEXT_1,
            SearchManager.SUGGEST_COLUMN_ICON_1,
            SearchManager.SUGGEST_COLUMN_INTENT_DATA};

    public SearchContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        return false;
    }

    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        String queryType = "";
        switch (uriMatcher.match(uri)) {
            case 1:
                String query = uri.getLastPathSegment().toLowerCase();
                return getSearchResultsCursor(query);
            default:
                return null;
        }
    }

    private MatrixCursor getSearchResultsCursor(String query) {
        MatrixCursor searchResults = new MatrixCursor(matrixCursorColumns);
        Object[] mColumn = new Object[4];
        int counterId = 0;
        if (query != null) {
            query = query.toLowerCase();

            for (String rec : getItems()) {
                if (rec.toLowerCase().contains(query)) {
                    mColumn[0] = "" + counterId++;
                    mColumn[1] = rec;

                    mColumn[2] = getContext().getResources().getIdentifier(getItemName(rec),
                            "drawable", getContext().getPackageName());
                    mColumn[3] = "" + counterId++;

                    searchResults.addRow(mColumn);
                }
            }
        }
        return searchResults;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public List<String> getItems() {
        List<String> items = new ArrayList<>();
        items.add("burger");
        items.add("pizza");
        items.add("salad");
        items.add("pasta");
        items.add("cake");
        items.add("wine");
        items.add("beer");
        items.add("water");
        items.add("coffee");
        return items;
    }

    private String getItemName(String suggestion) {
        String suggestionWords[] = suggestion.split(" ");
        return suggestionWords[0].toLowerCase();
    }
}
