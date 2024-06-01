package com.example.android.softkeyboard.emoji;

import static androidx.core.content.ContextCompat.getDrawable;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.astuetz.PagerSlidingTabStrip;
import com.example.android.softkeyboard.R;

import java.util.ArrayList;

public class EmojiPagerAdapter extends PagerAdapter implements PagerSlidingTabStrip.IconTabProvider {
/*
    private final String[] TITLES = { "recent",
                                    "people",
                                    "things",
                                    "nature",
                                    "places",
                                    "symbols" };
*/
    private final int[] ICONS = {R.drawable.ic_emoji_recent_light_activated,
            R.drawable.ic_emoji_people_light_activated, R.drawable.ic_emoji_things_light_activated,
            R.drawable.ic_emoji_nature_light_activated, R.drawable.ic_emoji_places_light_activated,
            R.drawable.ic_emoji_symbols_light_activated};

    private Context context;
    private ViewPager pager;
    private ArrayList<View> pages;
    private int keyboardHeight;

    public EmojiPagerAdapter(Context context, ViewPager pager, int keyboardHeight) {
        super();

        this.context = context;
        this.pager = pager;
        this.keyboardHeight = keyboardHeight;
        this.pages = new ArrayList<View>();

        EmojiIcons icons = new Apple_EmojiIcons();
//        EmojiIcons icons = getPreferedIconSet();
        pages.add(new KeyboardSinglePageView(context, new RecentEmojiAdapter(context)).getView());
        pages.add(new KeyboardSinglePageView(context, new StaticEmojiAdapter(context, EmojiTexts.peopleEmojiTexts, icons.getPeopleIconIds())).getView());
        pages.add(new KeyboardSinglePageView(context, new StaticEmojiAdapter(context, EmojiTexts.thingsEmojiTexts, icons.getThingsIconIds())).getView());
        pages.add(new KeyboardSinglePageView(context, new StaticEmojiAdapter(context, EmojiTexts.natureEmojiTexts, icons.getNatureIconIds())).getView());
        pages.add(new KeyboardSinglePageView(context, new StaticEmojiAdapter(context, EmojiTexts.transEmojiTexts, icons.getTransIconIds())).getView());
        pages.add(new KeyboardSinglePageView(context, new StaticEmojiAdapter(context, EmojiTexts.otherEmojiTexts, icons.getOtherIconIds())).getView());

    }

    @Override
    public View instantiateItem(ViewGroup container, int position) {
        pager.addView(pages.get(position), position, keyboardHeight);
        return pages.get(position);
    }

    @Override
    public void destroyItem (ViewGroup container, int position, Object object) {
        pager.removeView(pages.get(position));
    }
/*
    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }
*/
    @Override
    public int getCount() {
        return ICONS.length;
//        return TITLES.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public int getPageIconResId(int position) {
        return ICONS[position];
    }
/*
    private EmojiIcons getPreferedIconSet() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(pager.getContext());

        if (sharedPreferences
                .getString(MainSettings.CHANGE_ICON_SET_KEY, MainSettings.CHANGE_ICON_SET_VALUE_DEFAULT)
                .equals(MainSettings.CHANGE_ICON_SET_VALUE_GOOGLE)){
            return new Google_EmojiIcons();
        } else if (sharedPreferences
                .getString(MainSettings.CHANGE_ICON_SET_KEY, MainSettings.CHANGE_ICON_SET_VALUE_DEFAULT)
                .equals(MainSettings.CHANGE_ICON_SET_VALUE_APPLE)) {
            return new Apple_EmojiIcons();
        }

        return new Google_EmojiIcons();
    }

 */
}
