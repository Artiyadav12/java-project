package com.search.data.service.impl;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProcessData {

ExecutorService executorService=Executors.newSingleThreadExecutor();

    public void processDataForProduct(){




    //executorService.execute(new ThreadClass("To be ex"));



    }






    public class ThreadClass implements Callable{
        private String data;

        public ThreadClass(String data) {
            this.data = data;
        }

        @Override
        public Object call() throws Exception {

            return "Logic";
        }
    }
}
