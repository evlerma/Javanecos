package org.javahispano.javacup.tacticas.tacticas_aceptadas.javanecos;

import org.javahispano.javacup.model.*;
import org.javahispano.javacup.model.util.Constants;
import org.javahispano.javacup.model.util.Position;
import org.javahispano.javacup.model.engine.GameSituations;
import org.javahispano.javacup.model.command.*;
import org.javahispano.javacup.tacticas.tacticas_aceptadas.arturo8a.TacticaPulgarcitos;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Javanecos implements Tactic {

	// Lista de comandos
    LinkedList<Command> comandos = new LinkedList<Command>();

    // Alineaciones
    private final Position alineacionBase[]=new Position[]{
        new Position(0.0,-47.98642533936651),
        new Position(17.11888111888112,-32.30769230769231),
        new Position(0.2595419847328244,-31.082089552238806),
        new Position(-18.307692307692307,-2.6131221719457014),
        new Position(14.74125874125874,-3.088235294117647),
        new Position(-16.643356643356643,21.61764705882353),
        new Position(-17.11888111888112,-32.07013574660634),
        new Position(14.503496503496503,22.092760180995477),
        new Position(-26.867132867132867,32.07013574660634),
        new Position(-0.23776223776223776,12.352941176470589),
        new Position(24.965034965034967,31.357466063348415)
    };

    private final Position alineacionSaque[]=new Position[]{
        new Position(0.23776223776223776,-48.223981900452486),
        new Position(17.356643356643357,-32.54524886877828),
        new Position(0.0,-23.993212669683256),
        new Position(-14.265734265734267,-7.839366515837104),
        new Position(12.839160839160838,-7.601809954751132),
        new Position(-26.391608391608393,-1.4253393665158371),
        new Position(-16.643356643356643,-31.119909502262445),
        new Position(2.377622377622378,-1.4253393665158371),
        new Position(17.832167832167833,-0.9502262443438915),
        new Position(-5.944055944055944,-1.4253393665158371),
        new Position(27.34265734265734,-1.4253393665158371)
    };

    private final Position alineacionDefensa[]=new Position[]{
        new Position(0.23776223776223776,-48.223981900452486),
        new Position(17.356643356643357,-32.54524886877828),
        new Position(0.0,-23.993212669683256),
        new Position(-18.545454545454543,-16.391402714932127),
        new Position(7.608391608391608,-13.778280542986426),
        new Position(-26.62937062937063,-1.900452488687783),
        new Position(-16.643356643356643,-31.119909502262445),
        new Position(10.6993006993007,-1.6628959276018098),
        new Position(22.349650349650346,-16.628959276018097),
        new Position(-13.79020979020979,-1.900452488687783),
        new Position(25.202797202797203,-2.1380090497737556)
    };

    
    
    // Devuelve los jugadores a la posición inicial (sacando)
    public Position[] getStartPositions(GameSituations sp) {
    		return alineacionSaque;
    }

    // Devuelve los jugadores a la posición inicial (no sacando)
    public Position[] getNoStartPositions(GameSituations sp) {
        return alineacionDefensa;
    }

    // 
    public TacticDetail getDetail() {
        return new JavanecosTactilDetail();
    }

    @Override
    // TODO este es el ejemplo de la web: modificar
    public List<Command> execute(GameSituations sp) {
        comandos.clear();
        alineacionBase(sp);
        
        //Si no saca el rival
//        if (!sp.isRivalStarts()) {
            //Obtiene los datos de recuperacion del balon
            int[] recuperadores = sp.getRecoveryBall();
            //Si existe posibilidad de recuperar el balon
            if (recuperadores.length > 1) {
                //Obtiene las coordenadas del balon en el instante donde
                //se puede recuperar el balon
                double[] posRecuperacion = sp.getTrajectory(recuperadores[0]);
                //Recorre la lista de jugadores que pueden recuperar
                for (int i = 1; i < recuperadores.length; i++) {
                     //Ordena a los jugadores recuperadores que se ubique
                     //en la posicion de recuperacion
                     comandos.add(new CommandMoveTo(recuperadores[i],
                              new Position(posRecuperacion[0], posRecuperacion[1])));
                }
            }
//        }
        //Instancia un generador aleatorio
        Random r = new Random();
        //Recorre la lista de mis jugadores que pueden rematar
        for (int i : sp.canKick()) {
            //Si el jugador es de indice 8 o 10
            if (i == 8 || i == 10) {
                //condicion aleatoria
                if (r.nextBoolean()) {
                     //Ordena que debe rematar al centro del arco
                     comandos.add(new CommandHitBall(i, Constants.centroArcoSup, 1, 12 + r.nextInt(6)));
                } else if (r.nextBoolean()) {
                     //Ordena que debe rematar al poste derecho
                     comandos.add(new CommandHitBall(i, Constants.posteDerArcoSup, 1, 12 + r.nextInt(6)));
                } else {
                     //Ordena que debe rematar al poste izquierdo
                     comandos.add(new CommandHitBall(i, Constants.posteIzqArcoSup, 1, 12 + r.nextInt(6)));
                }
            } else {
                //inicia contador en cero
                int count = 0;
                int jugadorDestino;
                //Repetir mientras el jugador destino sea igual al jugador que remata
                while (((jugadorDestino = r.nextInt(11)) == i
                         //o mientras la coordenada y del jugador que remata
                         //es mayor que la coordenada y del que recibe
                         || sp.myPlayers()[i].getY() > sp.myPlayers()[jugadorDestino].getY())
                         //Y mientras el contador es menor a 20
                         && count < 20) {
                     //incrementa el contador
                     count++;
                }
                //Si el receptor del balon es el que remata
                if (i == jugadorDestino) {
                     while ((jugadorDestino = r.nextInt(sp.myPlayers().length)) == i) {
                     }
                }
                //Ordena que el jugador que puede rematar que de un pase
                //al jugador destino
                comandos.add(new CommandHitBall(i, sp.myPlayers()[jugadorDestino], 1, r.nextInt(45)));
            }
        }
        //Retorna la lista de comandos
        return comandos;
    }

    private void alineacionBase(GameSituations sp) {
        for (int i = 0; i < sp.myPlayers().length; i++) {
            //Ordena a cada jugador que se ubique segun la alineacion1
            comandos.add(new CommandMoveTo(i, alineacionBase[i]));
        }
    }
}