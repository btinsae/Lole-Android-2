package fanos.com.lole.repositories;

import android.arch.lifecycle.LiveData;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import fanos.com.lole.database.MenuDao;
import fanos.com.lole.model.ServiceProviderMenu;
import fanos.com.lole.utils.AppExecutors;
import fanos.com.lole.webservice.MenuService;
import retrofit2.Response;

@Singleton
public class MenuRepository {
    private final MenuService menu;
    private final MenuDao menuDao;
    private static final long FRESH_TIMEOUT=12000;

    @Inject
    public MenuRepository(MenuService menu, MenuDao menuDao) {
        this.menu = menu;
        this.menuDao = menuDao;
    }

    public LiveData<List<ServiceProviderMenu>> getMenu(int serviceProviderId) {
        refreshMenu(serviceProviderId);
        return menuDao.loadAllMenu();
    }

    private void refreshMenu(final int serviceProviderId) {
        AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                boolean menuExist = menuDao.hasMenu(FRESH_TIMEOUT);
                if (!menuExist) {
                    try {
                        Response response = menu.getMenu(serviceProviderId).execute();
                        menuDao.save((ServiceProviderMenu) response.body());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }
}
