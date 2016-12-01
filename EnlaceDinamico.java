abstract class A {
  public int f() { return 0; }
  abstract public int g();
}

class B extends A {
  @Override
  public int g() { return 1; }
}

class C extends B {
  @Override 
  public int f() { return 2; }
  public int h() { return 3; }
}  

public class EnlaceDinamico {
  public static final void main(String args[]) {
    A objA;
    B objB = new B();
    C objC = new C();
    objA = objC;
    
    objC.h();
    objA.f();
    objA.g();
    objA.h();
  }
}