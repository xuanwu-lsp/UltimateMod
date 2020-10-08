package xuanwu.ultimate;

public class Control
{
  private class Tick
    extends Thread
  {
    Control control;
    
    private Tick(Control control)
    {
      this.control = control;
    }
    
    private void Run()
    {
      for (;;)
      {
        this.control.OnTick();
      }
    }
  }
  
  public Control()
  {
    Thread tick = new Tick(this);
    tick.run();
  }
  
  public void OnTick() {}
}
