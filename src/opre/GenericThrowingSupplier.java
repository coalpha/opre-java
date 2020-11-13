package opre;

public interface GenericThrowingSupplier<T, U extends Throwable> {
   T get() throws U;
}
