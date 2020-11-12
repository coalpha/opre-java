package opre;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.Consumer;

public class Err<dummy_t, err_t> implements Result<dummy_t, err_t> {
   private final err_t val;

   public Err(err_t val) {
      this.val = val;
   }

   @Override
   public boolean is_ok() {
      return false;
   }

   @Override
   public boolean is_err() {
      return true;
   }

   @Override
   public None<dummy_t> ok() {
      return Option.None();
   }

   @Override
   public Option<err_t> err() {
      return new Some<err_t>(this.val);
   }

   @Override
   public dummy_t expect(String msg) {
      Panic.Panic(msg);
      return null;
   }

   @Override
   public dummy_t unwrap() {
      this.expect("Called Result.unwrap on Err");
      return null;
   }

   @Override
   public dummy_t unwrap_or(dummy_t def) {
      return def;
   }

   @Override
   public dummy_t unwrap_or_else(Supplier<dummy_t> fn) {
      return fn.get();
   }

   @Override
   @SuppressWarnings("unchecked")
   public <U> Result<U, err_t> map(Function<dummy_t, U> drop) {
      return (Result<U, err_t>) this;
   }

   @Override
   public String toString() {
      var sb = new StringBuilder("Err(");
      sb.append(this.val.toString());
      sb.append(')');
      return sb.toString();
   }

   @Override
   public void if_ok(Consumer<dummy_t> drop) {}

   @Override
   public void if_err(Consumer<err_t> fn) {
      fn.accept(this.val);
   }

   @Override
   public void with_both(Consumer<dummy_t> drop, Consumer<err_t> fn) {
      fn.accept(this.val);
   }

   @Override
   public <U> U fork(Function<dummy_t, U> drop, Function<err_t, U> fn) {
      return fn.apply(this.val);
   }
}
