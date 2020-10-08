package xuanwu.ultimate;

public class ExceptionHandler implements Thread.UncaughtExceptionHandler{
	 
	 
    private static ExceptionHandler cauchExceptionHandler = null;
 
    private ExceptionHandler(){
 
    }

    public static ExceptionHandler getInstance(){
 
        if (cauchExceptionHandler == null){
 
            synchronized (ExceptionHandler.class){
 
                if (cauchExceptionHandler == null){
                    cauchExceptionHandler = new ExceptionHandler();
                }
            }
        }
 
        return  cauchExceptionHandler;
    }
 
    public void setDefaultUnCachExceptionHandler(){ //在Application开始时调用
 
       Thread.setDefaultUncaughtExceptionHandler(this); //设置应用默认的全局捕获异常器
    }
    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
UltimateMod.logger.error("Ultimate在这个线程中捕获了一个错误:"+thread.getName());
for(int i = 0;i < throwable.getStackTrace().length;i++) {
	UltimateMod.logger.error("引发错误的类名:"+throwable.getStackTrace()[i].getClassName());
	UltimateMod.logger.error("引发错误的方法名:"+throwable.getStackTrace()[i].getMethodName());
	UltimateMod.logger.error("引发错误的文件名:"+throwable.getStackTrace()[i].getFileName());
	UltimateMod.logger.error("错误行数:"+throwable.getStackTrace()[i].getLineNumber());
}
    }
}
