public class MA {
    public String r, r2;
    double xi, e ;
    int i;
    public MA() {
        xi = 0;
        e = 0;
        i = 0;
    }
    public String it(double x, int cs){
        double vv = Math.exp(x);
        r2 = null;
        xi = e;
        e = e + Math.pow(x, i)/fact(i);
        r = r + "\n"+Math.round(i*Math.pow(10, cs))/Math.pow(10, cs)+"              "+Math.round(e*Math.pow(10, cs))/Math.pow(10, cs)+ "             "+Math.round(et(vv,e)*Math.pow(10, cs))/Math.pow(10, cs)+"%             "+Math.round(ea(e, xi)*Math.pow(10, cs))/Math.pow(10, cs) +"%";
        
        if (ea(e,xi)>=tol(cs)) {
            i++;
            it(x,cs);
        } else {
            i = 0;
            r2 = r;
            r = "i             xi             ea%";
            xi = 0;
            e = 0;
            return r2;
        }
        return r2;
    }//método iteración
    public String bsc(double xl, double xu, double masa, double velocidad, double tiempo, int cs){
        r2 = null;
    	double xr = (xl+xu)/2;
    	r = r+"\n"+Math.round(i*Math.pow(10, cs))/Math.pow(10, cs)+"              "+Math.round(xl*Math.pow(10, cs))/Math.pow(10, cs) + "             "+Math.round(xu*Math.pow(10, cs))/Math.pow(10, cs)+"             "+Math.round(xr*Math.pow(10, cs))/Math.pow(10, cs)+"             " + Math.round(ea(xr, xi)*Math.pow(10, cs))/Math.pow(10, cs);
        i++;
        if (f1(xr, masa, velocidad, tiempo)*f1(xl, masa, velocidad, tiempo)==0 || ea(xi, xr)<tol(cs)){
            i = 0;
            r2 = r;
            r = "i             xi             ea%";
            xi = 0;
        }else{if(f1(xr, masa, velocidad, tiempo)*f1(xl, masa, velocidad, tiempo)<0){
            xi = xr;
            bsc(xl,xi, masa, velocidad, tiempo, cs);
        }else{
            if(f1(xr, masa, velocidad, tiempo)*f1(xl, masa, velocidad, tiempo)>0){
                xi = xr;
                bsc(xi, xu, masa, velocidad, tiempo,cs);
            }
        }
        }
        return r2;
    }//método bisección
    public String fPs(double xl, double xu,double masa, double velocidad, double tiempo, int cs){
    	double xr = xu-((f1(xu, masa, velocidad, tiempo)*(xl-xu))/(f1(xl, masa, velocidad, tiempo)-f1(xu, masa, velocidad, tiempo)));
        r = r+"\n"+Math.round(i*Math.pow(10, cs))/Math.pow(10, cs)+"              "+Math.round(xl*Math.pow(10, cs))/Math.pow(10, cs) + "             "+Math.round(xu*Math.pow(10, cs))/Math.pow(10, cs)+"             "+Math.round(xr*Math.pow(10, cs))/Math.pow(10, cs)+"             " + Math.round(ea(xr, xi)*Math.pow(10, cs))/Math.pow(10, cs);
        xi = xr;
        i++;
        System.out.println(f1(xl, masa, velocidad, tiempo));
    	if(f1(xi, masa, velocidad, tiempo)*f1(xl, masa, velocidad, tiempo)<0){
            
    		fPs(xl,xi, masa, velocidad, tiempo, cs);
    	}else{
    		if(f1(xi, masa, velocidad, tiempo)*f1(xl, masa, velocidad, tiempo)>0){
                
    			fPs(xi, xu, masa, velocidad, tiempo, cs);
    		}else{if(f1(xi, masa, velocidad, tiempo)*f1(xl, masa, velocidad, tiempo)==0 || ea(xr, xi)<0.05 ) {
                i = 0;
                r2 = r;
                r = "i             xi             ea%";
                xi = 0;
    		}
    		}
    	}
    	return r2;
    }//método falsa posición
    public String nR(double x0, int cs) {
        r2 = null;
        xi = x0 - ((f(x0)) / (fP(x0)));
        r = r+"\n"+Math.round(i*Math.pow(10, cs))/Math.pow(10, cs)+"              "+Math.round(xi*Math.pow(10, cs))/Math.pow(10, cs) + "             " + Math.round(ea(xi, x0)*Math.pow(10, cs))/Math.pow(10, cs);
        i++;
        if (ea(xi, x0) > tol(cs)) {
            nR(xi, cs);
        }else{
            for (int j = 0; j < 3; j++)
            i = 0;
            r2 = r;
            r = "i             xi             ea%";
            xi = 0;
        }
        return r2;
    }//método Newton-Raphson
    public String sec(double x0, double x1, int cs) {
        xi = x0 - ((f(x0) * (x1 - x0)) / (f(x1) - f(x0)));
        x0 = x1;
        x1 = xi;
        r = r+"\n"+Math.round(i*Math.pow(10, cs))/Math.pow(10, cs)+"              "+Math.round(xi*Math.pow(10, cs))/Math.pow(10, cs) + "             " + Math.round(ea(xi, x0)*Math.pow(10, cs))/Math.pow(10, cs);
        i++;
        if (ea(xi, x0) > tol(cs)) {
            sec(x0, x1, cs);
        }else{
            for (int j = 0; j < 3; j++)
            i = 0;
            r2 = r;
            r = "i             xi             ea%";
            xi = 0;
        }
        return r2;
    }//método secante
    public double f1(double x,double masa, double velocidad, double tiempo) {
        return(((9.81*masa)/x)*(1-Math.exp(-1*(x/masa)*tiempo))-velocidad);
    }//función para bsc y fP
    public double f(double x) {
        return (Math.exp(-x) - x);
    }//función para nR y sec
    public double fP(double x) {
        return -Math.exp(-x) - 1;
    }//función derivada para nR y sec
    public double ea(double v, double van) {
        return Math.abs((v - van) / v) * 100;
    }//Error aproximado
    public double et(double vv,double va){
        return (Math.abs(vv-va)/vv)*100;
    }
    public int fact(int i){
        int r = i;
        if(i !=0){
        for (int j = i; i > 1; j--) {
        r = r * (i-1);
        i--;
        }
        }else{
            r = 1;
        }
        return r;
    }//factorial
    public double tol(int ss){ 
        return ((0.5)*Math.pow(10, (2-ss)))*100;
    }//tolerancia
}