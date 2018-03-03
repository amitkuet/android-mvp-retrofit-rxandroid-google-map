package app.engine.android.activity.navigation;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import app.engine.android.AppEngine;
import app.engine.android.R;

public class NavigationDrawerMenuView {

    public void setView(Activity activity, final Context context, LinearLayout menuList, final DrawerLayout drawerLayout) {
        LayoutInflater inflater = LayoutInflater.from(context);
        for (int i=0; i<AppEngine.getInstance().menuItemList.getMenuList(context).size(); i++) {
            View view  = inflater.inflate(R.layout.activity_main_navigation_menu_item, menuList, false);
            MenuItemViewHolder viewHolder = new MenuItemViewHolder();

            viewHolder.button = view.findViewById(R.id.navMenuButton);
            viewHolder.navButtonIcon = view.findViewById(R.id.navButtonIcon);
            view.setTag(viewHolder);

            MenuItemViewHolder holder = (MenuItemViewHolder) view.getTag();
            holder.button.setText(AppEngine.getInstance().menuItemList.getMenuList(context).get(i).getTitle());


            Drawable buttonIcon = ResourcesCompat.getDrawable(context.getResources(), AppEngine.getInstance().menuItemList.getMenuList(context).get(i).getImageResId(), null);
            buttonIcon.setBounds(0, 0, (int) (buttonIcon.getIntrinsicWidth() * 0.6), (int) (buttonIcon.getIntrinsicHeight() * 0.6));
            ScaleDrawable sd = new ScaleDrawable(buttonIcon, 0, 40, 40);

            holder.navButtonIcon.setImageDrawable(buttonIcon);
            final int finalI = i;
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AppEngine.getInstance().androidIntent.startActivity(context, AppEngine.getInstance().menuItemList.getMenuList(context).get(finalI).getActivityClass());
                    drawerLayout.closeDrawers();
                }
            });
            menuList.addView(view);
        }
    }

}

class MenuItemViewHolder {
    public Button button;
    public ImageView navButtonIcon;
}
