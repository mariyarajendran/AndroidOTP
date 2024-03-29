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

package com.amitshekhar.example.ui.screen.signupscreen;

import com.amitshekhar.example.ui.base.MvpView;

/**
 * Created by amitshekhar on 13/01/17.
 */

public interface SignupMvpView extends MvpView {

    void showSignupResponseData(String msg, PojoSignupUserDetails pojoSignupUserDetails, int event);

    void showResponseError(String error, int event);
}
