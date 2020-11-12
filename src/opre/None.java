package opre;

import java.util.function.Supplier;
import java.util.function.Consumer;
import java.util.function.Function;

public final class None<dummy_t> implements Option<dummy_t> {
   /** A pointer to the only None instance */
   public static final None<Object> ptr = new None<>();

   private None() {}

   @Override
   public boolean is_some() {
      return false;
   }

   @Override
   public boolean is_none() {
      return true;
   }

   @Override
   public dummy_t expect(final String msg) {
      Panic.Panic(msg);
      return null;
   }

   @Override
   public dummy_t unwrap() {
      this.expect("Called Option.unwrap on None");
      return null;
   }

   @Override
   public dummy_t unwrap_or(final dummy_t def) {
      return def;
   }

   @Override
   public dummy_t unwrap_or_else(Supplier<dummy_t> fn) {
      return fn.get();
   }

   @Override
   @SuppressWarnings("unchecked")
   public <U> Option<U> map(Function<dummy_t, U> drop) {
      return (Option<U>) this;
   }

   @Override
   public String toString() {
      return "None";
   }

   @Override
   public void if_some(Consumer<dummy_t> drop) {}

   @Override
   public void if_none(Runnable fn) {
      fn.run();
   }

   @Override
   public void with_both(Consumer<dummy_t> drop, Runnable fn) {
      fn.run();
   }

   @Override
   public <U> U fork(Function<dummy_t, U> drop, Supplier<U> fn) {
      return fn.get();
   }

   @Override
   public Object val_ptr() {
      return (Object) this;
   }
}
