package opre;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.Consumer;

public final class Ok<ok_t, dummy_t> implements Result<ok_t, dummy_t> {
   private final ok_t val;

   public Ok(final ok_t val) {
      this.val = val;
   }

   @Override
   public boolean is_ok() {
      return true;
   }

   @Override
   public boolean is_err() {
      return false;
   }

   @Override
   public Some<ok_t> ok() {
      return new Some<ok_t>(this.val);
   }

   @Override
   public Option<dummy_t> err() {
      return Option.None();
   }

   @Override
   public ok_t expect(final String drop) {
      return this.val;
   }

   @Override
   public ok_t unwrap() {
      return this.val;
   }

   @Override
   public ok_t unwrap_or(final ok_t drop) {
      return this.val;
   }

   @Override
   public ok_t unwrap_or_else(final Supplier<ok_t> drop) {
      return this.val;
   }

   @Override
   public <U> Result<U, dummy_t> map(final Function<ok_t, U> fn) {
      return new Ok<U, dummy_t>(fn.apply(this.val));
   }

   @Override
   public String toString() {
      final var sb = new StringBuilder("Ok(");
      sb.append(this.val.toString());
      sb.append(')');
      return sb.toString();
   }

   @Override
   public void if_ok(final Consumer<ok_t> fn) {
      fn.accept(this.val);
   }

   @Override
   public void if_err(final Consumer<dummy_t> drop) {}

   @Override
   public void with_both(final Consumer<ok_t> fn, final Consumer<dummy_t> drop) {
      fn.accept(this.val);
   }

   @Override
   public <U> U fork(final Function<ok_t, U> fn, final Function<dummy_t, U> drop) {
      return fn.apply(this.val);
   }
}
