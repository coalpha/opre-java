package opre;

import java.util.function.Supplier;
import java.util.function.Consumer;
import java.util.function.Function;

public class Some<some_t> implements Option<some_t> {
   private final some_t val;

   public Some(some_t data) {
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
   public some_t expect(String drop) {
      return this.val;
   }

   @Override
   public some_t unwrap() {
      return this.val;
   }

   @Override
   public some_t unwrap_or(Object drop) {
      return this.val;
   }

   @Override
   public some_t unwrap_or_else(Supplier<some_t> drop) {
      return this.val;
   }

   @Override
   public <U> Option<U> map(Function<some_t, U> fn) {
      return new Some<U>(fn.apply(this.val));
   }
   @Override
   public String toString() {
      var sb = new StringBuilder("Some(");
      sb.append(this.val.toString());
      sb.append(')');
      return sb.toString();
   }

   @Override
   public void if_some(Consumer<some_t> fn) {
      fn.accept(this.val);
   }

   @Override
   public void if_none(Runnable drop) {}

   @Override
   public void with_both(Consumer<some_t> fn, Runnable drop) {
      fn.accept(this.val);
   }

   @Override
   public <U> U fork(Function<some_t, U> fn, Supplier<U> drop) {
      return fn.apply(this.val);
   }

   @Override
   public Object val_ptr() {
      return (Object) this.val;
   }
}
