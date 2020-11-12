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
   T expect(String msg);
   T unwrap();
   T unwrap_or(T def);
   T unwrap_or_else(Supplier<T> fn);
   <U> Option<U> map(Function<T, U> fn);

   String toString();

   // my methods
   void if_some(Consumer<T> some);

   void if_none(Runnable none);

   void with_both(Consumer<T> some, Runnable none);

   <U> U fork(Function<T, U> some, Supplier<U> none);

   /**
    * If Some, a pointer to the wrapped value.
    * If None, a pointer to this.
    */
   Object val_ptr();

   static <some_t> Some<some_t> Some(some_t val) {
      return new Some<>(val);
   }

   @SuppressWarnings("unchecked")
   static <dummy_t> None<dummy_t> None() {
      return (None<dummy_t>) None.ptr;
   }

   static <T> Option<T> fromAny(T val) {
      if (val == null) {
         return new None<>();
      }
      return new Some<>(val);
   }
}
