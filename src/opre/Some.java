package opre;

import java.util.function.Supplier;
import java.util.function.Consumer;
import java.util.function.Function;

public final class Some<some_t> implements Option<some_t> {
   private final some_t val;

   public Some(final some_t data) {
      this.val = data;
   }

   @Override
   public boolean is_some() {
      return true;
   }

   @Override
   public boolean is_none() {
      return false;
   }

   @Override
   public some_t expect(final String drop) {
      return this.val;
   }

   @Override
   public some_t unwrap() {
      return this.val;
   }

   @Override
   public some_t unwrap_or(final Object drop) {
      return this.val;
   }

   @Override
   public some_t unwrap_or_else(final Supplier<some_t> drop) {
      return this.val;
   }

   @Override
   public <U> Option<U> map(final Function<some_t, U> fn) {
      return new Some<U>(fn.apply(this.val));
   }
   @Override
   public String toString() {
      final var sb = new StringBuilder("Some(");
      sb.append(this.val.toString());
      sb.append(')');
      return sb.toString();
   }

   @Override
   public void if_some(final Consumer<some_t> fn) {
      fn.accept(this.val);
   }

   @Override
   public void if_none(final Runnable drop) {}

   @Override
   public void with_both(final Consumer<some_t> fn, final Runnable drop) {
      fn.accept(this.val);
   }

   @Override
   public <U> U fork(final Function<some_t, U> fn, final Supplier<U> drop) {
      return fn.apply(this.val);
   }

   @Override
   public Object val_ptr() {
      return (Object) this.val;
   }
}
