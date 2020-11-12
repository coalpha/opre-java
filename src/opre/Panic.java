package opre;

public interface Panic {
   /** Never returns */
   public static void Panic() {
      System.err.println("Panic: ");
      final var stackTrace = Thread.currentThread().getStackTrace();
      final var stackLength = stackTrace.length;
      for (var i = 2; i < stackLength; i++) {
         System.err.println("   at " + stackTrace[i].toString());
      }
      System.exit(1);
   }

   /** Never returns */
   public static void Panic(final String msg) {
      System.err.println("Panic: " + msg);
      final var stackTrace = Thread.currentThread().getStackTrace();
      final var stackLength = stackTrace.length;
      for (var i = 2; i < stackLength; i++) {
         System.err.println("   at " + stackTrace[i].toString());
      }
      System.exit(1);
   }
}
