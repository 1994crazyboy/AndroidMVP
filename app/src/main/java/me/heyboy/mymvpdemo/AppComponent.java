package me.heyboy.mymvpdemo;

import dagger.Component;
import me.heyboy.mymvpdemo.photo.PhotoModule;

/**
 * Created by gcgx on 2017/11/9 0009.
 */

@Component(modules = {PhotoModule.class})
public interface AppComponent {
}
