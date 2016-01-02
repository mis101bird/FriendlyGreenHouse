package com.example.friendlygreenhouse.application;

import com.example.friendlygreenhouse.application.api.CallAPIhelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.assertj.android.api.Assertions.assertThat;


@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class MainActivityTest {

    private MainActivity mActivity;
    private CallAPIhelper apihelper;
    @Before
    public void setup() {
        mActivity = Robolectric.buildActivity(MainActivity.class).create().get();
        apihelper=new CallAPIhelper();
    }

    @Test
    public void mainActivityAppearsAsExpectedInitially() {
        apihelper.sendChangeFlowerOrder(mActivity,"003","NTOU");
        //assertThat(mActivity.mClickMeButton).hasText("Click me!");
        //assertThat(mActivity.mHelloWorldTextView).hasText("Hello world!");
    }
/*
    @Test
    public void clickingClickMeButtonChangesHelloWorldText() {
        assertThat(mActivity.mHelloWorldTextView).hasText("Hello world!");
        mActivity.mClickMeButton.performClick();
        assertThat(mActivity.mHelloWorldTextView).hasText("HEY WORLD");
    }
*/
}