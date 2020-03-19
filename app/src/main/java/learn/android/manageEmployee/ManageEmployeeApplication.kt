package learn.android.manageEmployee

import android.app.Application
import learn.android.manageEmployee.di.ApplicationComponent
import learn.android.manageEmployee.di.ApplicationModule
import learn.android.manageEmployee.di.DaggerApplicationComponent

/**
 * Created by Aswathy on 3/13/2020.
 */
class ManageEmployeeApplication : Application() {

    companion object {
        lateinit var appComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }



}