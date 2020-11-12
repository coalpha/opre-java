package opre;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.Consumer;

/**
* Rust's <code>std::Result</code> ported to Java
* @see https://doc.rust-lang.org/std/result/enum.Result.html
*/
public interface Result<ok_t, err_t> {
   boolean is_ok();
   boolean is_err();
   Option<ok_t> ok();
   Option<err_t> err();
   ok_t expect(String msg);
   ok_t unwrap();
   ok_t unwrap_or(ok_t def);
   ok_t unwrap_or_else(Supplier<ok_t> fn);
   <U> Result<U, err_t> map(Function<ok_t, U> fn);

   String toString();

   // my methods
   void if_ok(Consumer<ok_t> ok);

   void if_err(Consumer<err_t> err);

   void with_both(Consumer<ok_t> ok, Consumer<err_t> err);

   <U> U fork(Function<ok_t, U> ok, Function<err_t, U> err);

   static <ok_t, dummy_t> Ok<ok_t, dummy_t> Ok(ok_t val) {
      return new Ok<>(val);
   }

   static <dummy_t, err_t> Err<dummy_t, err_t> Err(err_t val) {
      return new Err<>(val);
   }

   static <ok_t> Result<ok_t, Throwable> trycatch(ThrowingSupplier<ok_t> fn) {
      try {
         return new Ok<>(fn.get());
      } catch (Throwable e) {
         return new Err<>(e);
      }
   }

   static void ignore(ThrowingRunnable fn) {
      try {
         fn.run();
      } catch (Throwable e) {}
   }
}
