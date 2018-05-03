package nadav.tasher.textfactory;

import android.os.AsyncTask;

public class RunInBackground extends AsyncTask<RunInBackground.Process,String,String> {

    Processed onFinish;
    Process[] preProcesses;

    public RunInBackground(Processed ps,Process... pre){
        onFinish=ps;
        preProcesses=pre;
    }

    @Override
    protected String doInBackground(Process... processes) {
        StringBuilder builder=new StringBuilder();
        for (Process process:preProcesses) {
            builder.append(process.process());
        }
        for (Process process:processes) {
            builder.append(process.process());
        }
        return builder.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(onFinish!=null){
            onFinish.processed(s);
        }
    }

    public interface Process{
        String process();
    }
    public interface Processed{
        void processed(String result);
    }
}
