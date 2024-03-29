/*
 *    Copyright (C) 2018 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.amitshekhar.example.injection.component;

import android.app.Application;
import android.content.Context;

import com.amitshekhar.example.MyApplication;
import com.amitshekhar.example.data.ServiceRequest;
import com.amitshekhar.example.data.local.PreferencesHelper;
import com.amitshekhar.example.injection.annotation.ApplicationContext;
import com.amitshekhar.example.injection.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by amitshekhar on 13/01/17.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(MyApplication myApplication);

    @ApplicationContext
    Context context();

    Application application();

    ServiceRequest serviceRequest();

    PreferencesHelper preferencesHelper();

    // DataManager dataManager();

}
