public abstract class RealNumber implements Number {

	public abstract double getDouble();

	@Override
	public abstract RealNumber add(Number num);
	
	@Override
	public abstract RealNumber subtract(Number num);

	@Override
	public abstract RealNumber multiply(Number num);

	@Override
	public abstract RealNumber divide(Number num);

}