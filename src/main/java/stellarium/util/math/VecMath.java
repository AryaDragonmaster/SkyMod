package stellarium.util.math;

import sciapi.api.temporaries.Temporal;
import sciapi.api.value.IValRef;
import sciapi.api.value.STempRef;
import sciapi.api.value.euclidian.CrossUtil;
import sciapi.api.value.euclidian.ECoord;
import sciapi.api.value.euclidian.EVector;
import sciapi.api.value.euclidian.IEVector;
import sciapi.api.value.numerics.DDouble;
import sciapi.api.value.numerics.DDoubleSet;
import sciapi.api.value.numerics.IReal;
import sciapi.api.value.numerics.NumMath;
import sciapi.api.value.util.BOp;
import sciapi.api.value.util.VOp;

public class VecMath {
	
	/** Gives Added value for EVector. */
	@Temporal
	public static IValRef<EVector> add(IValRef<EVector> par1, IValRef<EVector> par2)
	{
		return BOp.add(par1, par2);
	}
	
	/** Gives Subtracted value for EVector. */
	@Temporal
	public static IValRef<EVector> sub(IValRef<EVector> par1, IValRef<EVector> par2)
	{
		return BOp.sub(par1, par2);
	}
	
	
	/**
	 * Gives Multiplicated value for EVector.
	 * */
	@Temporal
	public static IValRef<EVector> mult(IValRef<IReal> par1, IValRef<EVector> par2)
	{
		return VOp.mult(par1, par2);
	}
	
	/**
	 * Gives Divided value for EVector.
	 * */
	@Temporal
	public static IValRef<EVector> div(IValRef<IReal> par1, IValRef<EVector> par2)
	{
		return VOp.div(par1, par2);
	}
	
	/**
	 * Gives Multiplicated value for EVector.
	 * */
	@Temporal
	public static IValRef<EVector> mult(double par1, IValRef<EVector> par2)
	{
		IValRef<DDouble> ref = DDoubleSet.ins.getNew();
		ref.getVal().set(par1);
		return VOp.mult(ref, par2);
	}
	
	/**
	 * Gives Divided value for EVector.
	 * */
	@Temporal
	public static IValRef<EVector> div(double par1, IValRef<EVector> par2)
	{
		IValRef<DDouble> ref = DDoubleSet.ins.getNew();
		ref.getVal().set(par1);
		return VOp.div(ref, par2);
	}
	
	
	/**
	 * Gives Dot product of Euclidian Vectors.
	 * */
	@Temporal
	public static IValRef<IReal> dot(IValRef<EVector> par1, IValRef<EVector> par2)
	{
		return VOp.dot(par1, par2);
	}

	/**
	 * Gives Normalized EVector.
	 * */
	@Temporal
	public static IValRef<EVector> normalize(IValRef<EVector> par)
	{
		return VOp.normalize(par);
	}
	
	@Temporal
	public static IValRef<IReal> size2(IValRef<EVector> par)
	{
		return VOp.size2(par);
	}
	
	@Temporal
	public static IValRef<IReal> size(IValRef<EVector> par)
	{
		return NumMath.sqrt.calc(size2(par));
	}
	
	public static IReal getCoord(IValRef<EVector> par, int N)
	{
		return par.getVal().getCoord(N);
	}
	
	public static int getDimension(IValRef<EVector> par)
	{
		return par.getVal().getDimension();
	}
	
	/**
	 * Projection of target to pole
	 * @param vec normalized polar vector
	 * @param tar the target vector
	 * */
	public static final IValRef<EVector> projection(EVector vec, IValRef<EVector> tar){
		return VecMath.mult(VecMath.dot(vec,tar), vec);
	}
	
	/**
	 * Projection of target to Polar plane
	 * @param pol normalized polar vector
	 * @param tar the target vector
	 * */
	public static final IValRef<EVector> projectionToPlane(EVector pol, EVector tar){
		return VecMath.sub(tar, projection(pol, tar));
	}
	
	
	public static double getX(IValRef<EVector> par)
	{
		return getCoord(par, 0).asDouble();
	}
	
	public static double getY(IValRef<EVector> par)
	{
		return getCoord(par, 1).asDouble();
	}
	
	public static double getZ(IValRef<EVector> par)
	{
		return getCoord(par, 2).asDouble();
	}

	public static IValRef<EVector> interpolate(EVector prevPos, EVector curPos, double partime) {
		return add(prevPos, mult(partime, sub(curPos, prevPos)));
	}
	
	public static double getAngle(EVector vec1, EVector vec2) {
		return Spmath.getD(NumMath.atan2.calc(VecMath.size(CrossUtil.cross(vec1, vec2)), VecMath.dot(vec1, vec2)));
	}

	
	public static ECoord copyCoord(ECoord coord) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static EVector rotateVectorX(double d) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static EVector rotateVectorY(double d) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static EVector rotateVectorZ(double d) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static ECoord rotateCoordZ(ECoord coord, double angle) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static ECoord rotateCoordX(ECoord coord, double angle) {
		// TODO Auto-generated method stub
		return null;
	}

	public static ECoord rotateCoordY(ECoord coord, double angle) {
		// TODO Auto-generated method stub
		return null;
	}

	public static ECoord rotateCoord(ECoord coord, IEVector axis, double angle) {
		// TODO Auto-generated method stub
		return null;
	}

	public static ECoord getDefaultCoord() {
		EVector x = new EVector(1.0, 0.0, 0.0);
		EVector y = new EVector(0.0, 1.0, 0.0);
		EVector z = new EVector(0.0, 0.0, 1.0);
		
		return new ECoord(x, y, z);
	}
}
