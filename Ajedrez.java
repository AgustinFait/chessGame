
import java.util.LinkedList;


public class Ajedrez{

    public static void main(String[] args) {
        Tablero tablero = new Tablero();
        iniciarPiezas(tablero);
        tablero.mostrar();
    }

    private static void iniciarPiezas(Tablero t)
    {

        
        //negras
             t.agregarPieza((Pieza)(new Torre(0,7,true)));
        t.agregarPieza((Pieza)(new Peon(1,0,true)));
        t.agregarPieza((Pieza)(new Peon(1,1,true)));
        t.agregarPieza((Pieza)(new Peon(1,2,true)));
        t.agregarPieza((Pieza)(new Peon(1,3,true)));
        t.agregarPieza((Pieza)(new Peon(1,4,true)));
        t.agregarPieza((Pieza)(new Peon(1,5,true)));
        t.agregarPieza((Pieza)(new Peon(1,6,true)));
        t.agregarPieza((Pieza)(new Peon(1,7,true)));
        t.agregarPieza((Pieza)(new Caballo(0,6,true)));
        t.agregarPieza((Pieza)(new Caballo(0,1,true)));
        t.agregarPieza((Pieza)(new Alfil(0,2,true)));
        t.agregarPieza((Pieza)(new Alfil(0,5,true)));
        t.agregarPieza((Pieza)(new Torre(0,0,true)));
        t.agregarPieza((Pieza)(new Torre(0,7,true)));
        t.agregarPieza((Pieza)(new Reina(0,3,true)));
        t.agregarPieza((Pieza)(new Rey(0,4,true)));
        Tablero.punteroAPosicion(0,4).ocupando().movimientos  = Tablero.punteroAPosicion(0,4).ocupando().definirMovimientos(Tablero.punteroAPosicion(0,4));
        //blancas
        t.agregarPieza((Pieza)(new Peon(6,0,false)));
        t.agregarPieza((Pieza)(new Peon(6,1,false)));
        t.agregarPieza((Pieza)(new Peon(6,2,false)));
        t.agregarPieza((Pieza)(new Peon(6,3,false)));
        t.agregarPieza((Pieza)(new Peon(6,4,false)));
        t.agregarPieza((Pieza)(new Peon(6,5,false)));
        t.agregarPieza((Pieza)(new Peon(6,6,false)));
        t.agregarPieza((Pieza)(new Peon(6,7,false)));
        t.agregarPieza((Pieza)(new Caballo(7,1,false)));
        t.agregarPieza((Pieza)(new Caballo(7,6,false)));
        t.agregarPieza((Pieza)(new Alfil(7,2,false)));
        t.agregarPieza((Pieza)(new Alfil(7,5,false)));
        t.agregarPieza((Pieza)(new Torre(7,0,false)));
        t.agregarPieza((Pieza)(new Torre(7,7,false)));
        t.agregarPieza((Pieza)(new Reina(7,3,false)));
        t.agregarPieza((Pieza)(new Rey(7,4,false)));
        Tablero.punteroAPosicion(7,4).ocupando().movimientos  = Tablero.punteroAPosicion(7,4).ocupando().definirMovimientos(Tablero.punteroAPosicion(7,4));
    }
}


class Posicion{
    private int fila;
    private int columna;
    private Pieza ocupando = null;

    public int fila(){return fila;}
    public void fila(int fila){this.fila = fila;}
    public int columna(){return columna;}
    public void columna(int columna){this.columna = columna;}
    public boolean ocupada(){return ocupando != null;}
    public Pieza ocupando(){return ocupando;}
    public void ocupando(Pieza p){this.ocupando = p;}
    public boolean deMiEquipo(boolean color){return (color == ocupando.color);}

    public Posicion(int fila, int columna)
    {
        this.fila = fila;
        this.columna = columna;
    }

    public Posicion Desplazamiento(int x,int y)
    {
        return Tablero.punteroAPosicion(this.columna+y,this.fila+x);
    }
}


class Pieza{
    protected Posicion pos;
    protected LinkedList movimientos;
    protected boolean color;
    protected LinkedList Atacantes;
    protected LinkedList Atacados;
    protected LinkedList Defensores;
    protected LinkedList Defendidos;

    public Posicion posicion(){return pos;}
    public void posicion(Posicion pos){this.pos = pos;}
    public boolean color(){return color;}
    public LinkedList movimientos(){return movimientos;}

    protected LinkedList definirMovimientos(Posicion p)
    {
        return null;
    }

    public Pieza(){}
    public Pieza(int columna, int fila,boolean color)
    {
        this.pos = Tablero.punteroAPosicion(columna, fila);
        this.color = color;
        this.Atacantes = new LinkedList<Pieza>();
        this.Atacados = new LinkedList<Pieza>();
        // this.Defensores = new LinkedList<Pieza>();
        // this.Defendidos = new LinkedList<Pieza>();
    }

    protected Pieza agregarLinea(LinkedList<Posicion> lista,Posicion p, int x, int y,boolean color)
    {
        for (int i = 0;Tablero.posicionValida(p.Desplazamiento(i*x, i*y));i++){
            Posicion pos = p.Desplazamiento(i*x, i*y);
            if(pos.ocupada())
            {
                if(!pos.deMiEquipo(color))lista.add(pos);
                else return pos.ocupando();
                break;
            }
            else lista.add(pos);
        }
        return null;
    }

    protected Pieza agregarPosicion(LinkedList lista, Posicion pos,boolean color){
        if(Tablero.posicionValida(pos))
        {
            if(pos.ocupada())
            {
                if(pos.deMiEquipo(color))
                {
                    return pos.ocupando();
                }
                else lista.add(pos);
            }
            else lista.add(pos);
        }
        return null;
    }
    public void actualizarMovimientos(int columnas,int filas)
    {

    }

    public void ActualizarAtacados()
    {
        for (int i = 0; i < this.Atacados.size(); i++) {
            ((Pieza)Atacados.get(i)).sacarAtacante(this);
        }
        Atacados.clear();
        for (int i = 0; i < this.movimientos.size(); i++) 
        {
            Posicion pos = (Posicion)movimientos.get(i);
            if(pos.ocupada())
            {
                pos.ocupando().agregarAtacante(this);
                this.Atacados.add(pos.ocupando());
            }
        }
    }

    // public void avisarADefendidos(){}

    // public void sumarDefensores(){}
    // public void sumarDefendidos(Pieza p)
    // {

    // }
    public void sacarAtacante(Pieza p)
    {
        Atacantes.remove(p);
    }
    public void agregarAtacante(Pieza p)
    {
        Atacantes.add(p);
    }
}

class Caballo extends Pieza{
    @Override
    protected LinkedList definirMovimientos(Posicion p)
    {
        LinkedList<Posicion> pos = new LinkedList();
        agregarPosicion(pos,p.Desplazamiento(1,2),this.color);
        agregarPosicion(pos,p.Desplazamiento(-1,2),this.color);
        agregarPosicion(pos,p.Desplazamiento(1,-2),this.color);
        agregarPosicion(pos,p.Desplazamiento(-1,-2),this.color);
        agregarPosicion(pos,p.Desplazamiento(2,1),this.color);
        agregarPosicion(pos,p.Desplazamiento(-2,1),this.color);
        agregarPosicion(pos,p.Desplazamiento(2,-1),this.color);
        agregarPosicion(pos,p.Desplazamiento(-2,-1),this.color);
        return pos;
    }
    public Caballo(int x, int y, boolean color)
    {
        super(x,y,color);
        this.movimientos = this.definirMovimientos(this.pos);
    }

    @Override
    public String toString()
    {
        if(this.color)return "Cn";
        else return "Cb";
    }
}  

class Peon extends Pieza{
    boolean PrimerMov = true;

    @Override
    protected LinkedList definirMovimientos(Posicion p)
    {
        int i;
        if(color)i = 1;else i = -1;
        LinkedList<Posicion> posiciones = new LinkedList<>();
        Posicion puntero = pos.Desplazamiento(0,1*i);
        if(!puntero.ocupada())posiciones.add(puntero);
        puntero = pos.Desplazamiento(0,2*i);
        if(!puntero.ocupada() && PrimerMov)posiciones.add(puntero);
        puntero = pos.Desplazamiento(1,1*i);
        if(Tablero.posicionValida(puntero))if(puntero.ocupada())if(!puntero.deMiEquipo(this.color))posiciones.add(puntero);
        puntero = pos.Desplazamiento(-1,1*i);
        if(Tablero.posicionValida(puntero))if(puntero.ocupada())if(!puntero.deMiEquipo(this.color))posiciones.add(puntero);
        return posiciones;
    }
    public Peon(int x, int y, boolean color)
    {
        super(x,y,color);
        this.movimientos = this.definirMovimientos(this.pos);
    }
    @Override
    public String toString()
    {
        if(this.color == true)return "Pn";
        else return "Pb";
    }
}

class Alfil extends Pieza{

    @Override
    protected  LinkedList definirMovimientos(Posicion p)
    {
        LinkedList<Posicion> posiciones = new LinkedList<>();  
        agregarLinea(posiciones, p, 1, 1,this.color);
        agregarLinea(posiciones, p, -1, -1,this.color);
        agregarLinea(posiciones, p, -1, 1,this.color);
        agregarLinea(posiciones, p, 1, -1,this.color);
        return posiciones;
    }

    public Alfil(int x, int y, boolean color)
    {
        super(x,y,color);
        this.movimientos = this.definirMovimientos(this.pos);
    }
    @Override
    public String toString()
    {
        if(this.color)return "An";
        else return "Ab";
    }
}

class Torre extends Pieza{

    @Override
    protected LinkedList definirMovimientos(Posicion p)
    {
        LinkedList<Posicion> posiciones = new LinkedList<>();
        agregarLinea(posiciones, p, 1, 0,this.color);
        agregarLinea(posiciones, p, 0, 1,this.color);
        agregarLinea(posiciones, p, -1, 0,this.color);
        agregarLinea(posiciones, p, 0, -1,this.color);
        return posiciones;
    }
    public Torre(int x, int y, boolean color)
    {
        super(x,y,color);
        this.movimientos = this.definirMovimientos(this.pos);
    }
    @Override
    public String toString()
    {
        if(this.color)return "Tn";
        else return "Tb";
    }
}

class Reina extends Pieza{
    @Override
    protected  LinkedList definirMovimientos(Posicion p)
    {
        LinkedList<Posicion> posiciones = new LinkedList<>();
        agregarLinea(posiciones, p, 1, 0,this.color);
        agregarLinea(posiciones, p, 0, 1,this.color);
        agregarLinea(posiciones, p, -1, 0,this.color);
        agregarLinea(posiciones, p, 0, -1,this.color);
        agregarLinea(posiciones, p, 1, 1,this.color);
        agregarLinea(posiciones, p, -1, -1,this.color);
        agregarLinea(posiciones, p, -1, 1,this.color);
        agregarLinea(posiciones, p, 1, -1,this.color);
        return posiciones;
    }

    public Reina(int x, int y, boolean color)
    {
        super(x,y,color);
        this.movimientos = this.definirMovimientos(this.pos);
    }
    @Override
    public String toString()
    {
        if(this.color)return "Qn";
        else return "Qb";
    }
}

class Rey extends Pieza{

    @Override
    protected  LinkedList definirMovimientos(Posicion p)
    {
        LinkedList<Posicion> posiciones = new LinkedList<>();
        agregarPosicion(posiciones, p.Desplazamiento(1, 0),this.color);
        agregarPosicion(posiciones, p.Desplazamiento(1, 1),this.color);
        agregarPosicion(posiciones, p.Desplazamiento(0, 1),this.color);
        agregarPosicion(posiciones, p.Desplazamiento(-1, 1),this.color);
        agregarPosicion(posiciones, p.Desplazamiento(-1, 0),this.color);
        agregarPosicion(posiciones, p.Desplazamiento(-1, -1),this.color);
        agregarPosicion(posiciones, p.Desplazamiento(0, -1),this.color);
        agregarPosicion(posiciones, p.Desplazamiento(1, -1),this.color);
        return posiciones;
    }
    public Rey(int x, int y, boolean color)
    {
        super(x,y,color);
        this.movimientos = this.definirMovimientos(this.pos);
    }
    @Override
    public String toString()
    {
        if(this.color)return "Kn";
        else return "Kb";
    }
}
class Tablero{
    static Posicion matriz[][];

    static public boolean posicionValida(Posicion p)
    {
        return p != null;
    }

    static private  boolean posicionValida(int columna, int fila)
    {
        return ((fila >= 0) && fila < 8 && (columna >= 0) && columna < 8);
    }
    public Tablero()
    {
        Tablero.matriz= new Posicion[8][8];
        for(int columnas = 0;columnas < 8;columnas++)
        {
            for(int filas = 0;filas < 8;filas++)
            {
                matriz[columnas][filas] = new Posicion(filas,columnas);
            }
        }
    }

    public void mostrar()
    {
        for(int columnas = 0;columnas < 8;columnas++)
        {
            for(int filas = 0;filas < matriz.length;filas++)
            {
                if(matriz[columnas][filas].ocupada())
                {
                    System.out.print(matriz[columnas][filas].ocupando().toString() + " ");
                }
                else System.out.print("  ");
            }
            System.out.print("\n");
        }
    }
    static public Posicion punteroAPosicion(int columna,int fila)
    {
        if(!posicionValida(columna, fila))return null;
        return matriz[columna][fila];
    }

    public void agregarPieza(Pieza p)
    {
        Posicion pos = matriz[p.posicion().columna()][p.posicion().fila()];
        pos.ocupando(p);
    }
}