package com.example.myroom.Adapter;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.example.myroom.Fragment.Frag_Gyeonggi;
import com.example.myroom.Fragment.Frag_Incheon;
import com.example.myroom.Fragment.Frag_Seoul;
import com.example.myroom.Fragment.Frag_Seoul_t;

public class ContentsPagerAdapter extends FragmentStatePagerAdapter {

    private int mPageCount;
    private String dest_address;
    private String dest_keyword;
    private int rushHour;



    public ContentsPagerAdapter(FragmentManager fm, int pageCount, String dest_keyword, String dest_address, int rushHour) {

        super(fm);

        this.mPageCount = pageCount;
        this.dest_address=dest_address;
        this.rushHour=rushHour;
        this.dest_keyword=dest_keyword;

    }



    @Override

    public Fragment getItem(int position) {

        switch (position) {

            case 0:

                Frag_Seoul_t seoulFrag = new Frag_Seoul_t(dest_keyword,dest_address,rushHour);

                return seoulFrag;

            case 1:

                Frag_Gyeonggi gyeonggiFrag = new Frag_Gyeonggi(dest_keyword,dest_address,rushHour);

                return gyeonggiFrag;


            case 2:

                Frag_Incheon inchoenFrag = new Frag_Incheon(dest_keyword,rushHour);

                return inchoenFrag;



            default:

                return null;

        }

    }



    @Override

    public int getCount() {

        return mPageCount;

    }

}