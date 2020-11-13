package opre;

public interface ThrowingSupplier<T> extends GenericThrowingSupplier<T, Throwable> {
   T get() throws Throwable;
}
