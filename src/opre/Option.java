package opre;

import java.lang.Runnable;
import java.util.function.Supplier;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Rust's <code>std::Option</code> and <code>std::Result</code> ported to Java
 * @see https://doc.rust-lang.org/std/option/enum.Option.html
 * @see https://doc.rust-lang.org/std/result/enum.Result.html
 */
public interface Option<T> {
   boolean is_some();
   boolean is_none();
   T expect(final String msg);
   T unwrap();
   T unwrap_or(final T def);
   T unwrap_or_else(final Supplier<T> fn);
   <U> Option<U> map(final Function<T, U> fn);

   String toString();

   // my methods
   void if_some(final Consumer<T> some);

   void if_none(final Runnable none);

   void with_both(final Consumer<T> some, final Runnable none);

   <U> U fork(final Function<T, U> some, final Supplier<U> none);

   /**
    * If Some, a pointer to the wrapped value.
    * If None, a pointer to this.
    */
   Object val_ptr();

   static <some_t> Some<some_t> Some(final some_t val) {
      return new Some<>(val);
   }

   @SuppressWarnings("unchecked")
   static <dummy_t> None<dummy_t> None() {
      return (None<dummy_t>) None.ptr;
   }

   @SuppressWarnings("unchecked")
   static <T> Option<T> fromAny(final T val) {
      if (val == null) {
         return (None<T>) None.ptr;
      }
      return new Some<>(val);
   }
}
