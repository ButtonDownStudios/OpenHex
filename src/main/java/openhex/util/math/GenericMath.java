package openhex.util.math;

/**
 * Performs math with {@link Number}s with generic arguments.
 * Most of it is done with class/type checking.
 * 
 * @author MisterCavespider
 *
 */
public class GenericMath {

	/**
	 * Returns an empty array with the same type as nClass
	 * of length l.
	 * @param nClass	The class containing the type for the 
	 * @param l
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <N extends Number> N[] genericArray(Class<N> nClass, int l) {	
		if(nClass == Integer.class) 	{	return (N[])new Integer[l];	}
		else if(nClass == Long.class) 	{	return (N[])new Long[l];	}
		else if(nClass == Short.class)	{	return (N[])new Short[l];	}
		else if(nClass == Byte.class) 	{	return (N[])new Byte[l];	}
		else if(nClass == Float.class)	{	return (N[])new Float[l];	}
		else							{	return (N[])new Double[l];	}
	}
	
	@SuppressWarnings("unchecked")
	public static <N extends Number> N genericAdd(Class<N> nClass, Number a, Number b) {
		if(nClass == Integer.class) 	{	return (N)(Integer)(a.intValue()+b.intValue());		}
		else if(nClass == Long.class) 	{	return (N)(Long)(a.longValue()+b.longValue());		}
		else if(nClass == Short.class)	{	return (N)(Integer)(a.shortValue()+b.shortValue());	}	//!
		else if(nClass == Byte.class) 	{	return (N)(Integer)(a.byteValue()+b.byteValue());	}	//!
		else if(nClass == Float.class)	{	return (N)(Float)(a.floatValue()+b.floatValue());	}
		else							{	return (N)(Double)(a.doubleValue()+b.doubleValue());}
	}
	
	@SuppressWarnings("unchecked")
	public static <N extends Number> N genericSubtract(Class<N> nClass, Number a, Number b) {
		if(nClass == Integer.class) 	{	return (N)(Integer)(a.intValue()-b.intValue());		}
		else if(nClass == Long.class) 	{	return (N)(Long)(a.longValue()-b.longValue());		}
		else if(nClass == Short.class)	{	return (N)(Integer)(a.shortValue()-b.shortValue());	}	//!
		else if(nClass == Byte.class) 	{	return (N)(Integer)(a.byteValue()-b.byteValue());	}	//!
		else if(nClass == Float.class)	{	return (N)(Float)(a.floatValue()-b.floatValue());	}
		else							{	return (N)(Double)(a.doubleValue()-b.doubleValue());}
	}
	
	@SuppressWarnings("unchecked")
	public static <N extends Number> N genericMult(Class<N> nClass, Number a, Number b) {
		if(nClass == Integer.class) 	{	return (N)(Integer)(a.intValue()*b.intValue());		}
		else if(nClass == Long.class) 	{	return (N)(Long)(a.longValue()*b.longValue());		}
		else if(nClass == Short.class)	{	return (N)(Integer)(a.shortValue()*b.shortValue());	}	//!
		else if(nClass == Byte.class) 	{	return (N)(Integer)(a.byteValue()*b.byteValue());	}	//!
		else if(nClass == Float.class)	{	return (N)(Float)(a.floatValue()*b.floatValue());	}
		else							{	return (N)(Double)(a.doubleValue()*b.doubleValue());}
	}
	
	@SuppressWarnings("unchecked")
	public static <N extends Number> N genericDiv(Class<N> nClass, Number a, Number b) {
		if(nClass == Integer.class) 	{	return (N)(Integer)(a.intValue()/b.intValue());		}
		else if(nClass == Long.class) 	{	return (N)(Long)(a.longValue()/b.longValue());		}
		else if(nClass == Short.class)	{	return (N)(Integer)(a.shortValue()/b.shortValue());	}	//!
		else if(nClass == Byte.class) 	{	return (N)(Integer)(a.byteValue()/b.byteValue());	}	//!
		else if(nClass == Float.class)	{	return (N)(Float)(a.floatValue()/b.floatValue());	}
		else							{	return (N)(Double)(a.doubleValue()/b.doubleValue());}
	}
	
	@SuppressWarnings("unchecked")
	public static <N extends Number> N genericAbs(Class<N> nClass, Number a) {
		if(nClass == Integer.class) 	{	return ((Integer)a>0) ? (N) a : (N)(Number)(-a.intValue());	}
		else if(nClass == Long.class) 	{	return ((Long)a>0l) ? (N) a : (N)(Number)(-a.longValue());	}
		else if(nClass == Short.class)	{	return ((Short)a>0) ? (N) a : (N)(Number)(-a.shortValue());	}	//!
		else if(nClass == Byte.class) 	{	return ((Byte)a>0) ? (N) a : (N)(Number)(-a.byteValue());	}	//!
		else if(nClass == Float.class)	{	return ((Float)a>0f) ? (N) a : (N)(Number)(-a.floatValue());	}
		else							{	return ((Double)a>0d) ? (N) a : (N)(Number)(-a.doubleValue());	}
	}
	
	public static <N extends Number> double sum(N... values) {
		double sum = 0;
		for (int i = 0; i < values.length; i++) {
			sum += values[i].doubleValue();
		}
		return sum;
	}
	
	public static <N extends Number> double sqSum(N... values) {
		double sum = 0;
		for (int i = 0; i < values.length; i++) {
			sum += Math.pow(values[i].doubleValue(), 2);
		}
		return sum;
	}
	
	/**
	 * Returns a 'new' nClass.
	 * Pretty much useless.
	 * 
	 * @param nClass
	 * @return
	 */
	public static <N extends Number> Class<? extends Number> genericTypeCheck(Class<N> nClass) {
		if(nClass == Integer.class) 	{	return Integer.class;	}
		else if(nClass == Long.class) 	{	return Long.class;		}
		else if(nClass == Short.class)	{	return Short.class;		}
		else if(nClass == Byte.class) 	{	return Byte.class;		}
		else if(nClass == Float.class)	{	return Float.class;		}
		else							{	return Double.class;	}
	}
}
