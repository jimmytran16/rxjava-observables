package com.example.reactivejavatest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.reactivejavatest.models.Books;
import com.example.reactivejavatest.tests.Generate;

import javax.xml.validation.SchemaFactoryLoader;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private Button loadBtn;
    private String TAG = "MainActivity.class";
    private CompositeDisposable disposable = new CompositeDisposable();

    @Override //When the activity is destroyed then it will invoke this function
    protected void onDestroy() {
        Log.d(TAG,"Disposing");
        super.onDestroy();
        disposable.clear(); //removing all the subscribers/observers without disabling it
        //disposable.dispose(); get rid of the subs/observers completely and disabling it
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadBtn = (Button)findViewById(R.id.loadBtn);
        loadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Observable<Books> observableBook = Observable
                        .fromIterable(Generate.getBookList()) //convert the ListofBooks arraylist to the Observable object
                        .subscribeOn(Schedulers.io()) //which is the working thread
                        .observeOn(AndroidSchedulers.mainThread()); //thread to observer on

                Observable<Books> observableBook2 = Observable
                        .fromIterable(Generate.getBookList2()) //convert the ListofBooks arraylist to the Observable object
                        .subscribeOn(Schedulers.io()) //which is the working thread
                        .observeOn(AndroidSchedulers.mainThread()); //thread to observer on
                observableBook2.subscribe(new Observer<Books>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG,"onsubscribe called.");
                        disposable.add(d);
                    }

                    @Override
                    public void onNext(Books books) { //calls as the observable is iterating through the list
                        Log.d(TAG,"onNext called. Book title: "+books.getTitle());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG,"onError called. "+e);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG,"onComplete called: FINISHED!");
                    }
                });
                observableBook.subscribe(new Observer<Books>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG,"onsubscribe called.");
                        disposable.add(d);
                    }

                    @Override
                    public void onNext(Books books) { //calls as the observable is iterating through the list
                        Log.d(TAG,"onNext called. Book title: "+books.getTitle());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG,"onError called. "+e);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG,"onComplete called: FINISHED!");
                    }
                });
            }
        });

    }
}
