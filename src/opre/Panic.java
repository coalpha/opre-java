package opre;

public interface Panic {
   /** Never returns */
   public static void Panic() {
      System.err.println("Panic: ");
      var stackTrace = Thread.currentThread().getStackTrace();
      var stackLength = stackTrace.length;
      for (var i = 2; i < stackLength; i++) {
         System.err.println("   at " + stackTrace[i].toString());
      }
      System.exit(1);
   }

   /** Never returns */
   public static void Panic(String msg) {
      System.err.println("Panic: " + msg);
      var stackTrace = Thread.currentThread().getStackTrace();
      var stackLength = stackTrace.length;
      for (var i = 2; i < stackLength; i++) {
         System.err.println("   at " + stackTrace[i].toString());
      }
      System.exit(1);
   }
}
