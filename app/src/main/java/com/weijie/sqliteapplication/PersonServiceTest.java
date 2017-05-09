package com.weijie.sqliteapplication;

/**
 * Created by weijie on 17-5-8.
 */

import java.util.List;

import android.content.Context;
import android.util.Log;

public class PersonServiceTest{
    private static String TAG = "weijie";

    private static Context mContext;
    public PersonServiceTest(Context context) {
        mContext = context;
    }
    // OtherPersonService personService = new
    // OtherPersonService(context);
    // //不可以这么写，因为Android把context环境变量是在PersonServiceTest实例化后给他的

    public void testSave() throws Exception {
        PersonService personService = new PersonService(mContext);
        // personService.save(new Person("老猪", (short) 11));
        for (int i = 0; i < 10; i++) {
            personService.save(new Person("你" + i, (short) (i + 10)));
        }

    }

    public void testFind() throws Exception {
        PersonService personService = new PersonService(mContext);
        Person person = personService.find(1);
        Log.i(TAG, person.toString());
    }

    public void testUpdate() throws Exception {
        PersonService personService = new PersonService(mContext);
        Person person = personService.find(1);
        person.setName("lv");
        personService.update(person);
    }

    public void testDelete() throws Exception {
        PersonService personService = new PersonService(mContext);
        personService.delete(1, 2, 3);
    }

    public void testGetCount() throws Exception {
        PersonService personService = new PersonService(mContext);
        Log.i(TAG, String.valueOf(personService.getCount()));
    }

    public void testGetScrollData() throws Exception {
        PersonService personService = new PersonService(mContext);
        List<Person> persons = personService.getScrollData(0, 3);
        for (Person person : persons) {
            Log.i(TAG, person.toString());
        }
    }
}
