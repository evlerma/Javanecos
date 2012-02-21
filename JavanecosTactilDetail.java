package org.javahispano.javacup.tacticas.tacticas_aceptadas.javanecos;

import java.awt.Color;

import org.javahispano.javacup.model.PlayerDetail;
import org.javahispano.javacup.model.TacticDetail;
import org.javahispano.javacup.model.util.Position;
import org.javahispano.javacup.render.EstiloUniforme;

public class JavanecosTactilDetail implements TacticDetail {

    public String getTacticName() {
        return "Los Javañecos";
    }

    public String getCountry() {
        return "España";
    }

    public String getCoach() {
        return "Quique y Luis";
    }

    public Color getShirtColor() {
        return new Color(37, 191, 11);
    }

    public Color getShortsColor() {
        return new Color(0, 153, 153);
    }

    public Color getShirtLineColor() {
        return new Color(17, 24, 207);
    }

    public Color getSocksColor() {
        return new Color(255, 255, 204);
    }

    public Color getGoalKeeper() {
        return new Color(85, 159, 68        );
    }

    public EstiloUniforme getStyle() {
        return EstiloUniforme.FRANJA_VERTICAL;
    }

    public Color getShirtColor2() {
        return new Color(250, 225, 157);
    }

    public Color getShortsColor2() {
        return new Color(2, 208, 252);
    }

    public Color getShirtLineColor2() {
        return new Color(228, 59, 1);
    }

    public Color getSocksColor2() {
        return new Color(171, 121, 22);
    }

    public Color getGoalKeeper2() {
        return new Color(29, 140, 107        );
    }

    public EstiloUniforme getStyle2() {
        return EstiloUniforme.SIN_ESTILO;
    }

    class JugadorImpl implements PlayerDetail {

        String nombre;
        int numero;
        Color piel, pelo;
        double velocidad, remate, presicion;
        boolean portero;
        Position Position;

        public JugadorImpl(String nombre, int numero, Color piel, Color pelo,
                double velocidad, double remate, double presicion, boolean portero) {
            this.nombre=nombre;
            this.numero=numero;
            this.piel=piel;
            this.pelo=pelo;
            this.velocidad=velocidad;
            this.remate=remate;
            this.presicion=presicion;
            this.portero=portero;
        }

        public String getPlayerName() {
            return nombre;
        }

        public Color getSkinColor() {
            return piel;
        }

        public Color getHairColor() {
            return pelo;
        }

        public int getNumber() {
            return numero;
        }

        public boolean isGoalKeeper() {
            return portero;
        }

        public double getSpeed() {
            return velocidad;
        }

        public double getPower() {
            return remate;
        }

        public double getPrecision() {
            return presicion;
        }

    }

    public PlayerDetail[] getPlayers() {
        return new PlayerDetail[]{
            new JugadorImpl("Mooroh", 1, new Color(51,0,51), new Color(0,0,0),1.0d,1.0d,1.0d, true),
            new JugadorImpl("Antonio", 2, new Color(255,200,150), new Color(50,0,0),1.0d,1.0d,0.7d, false),
            new JugadorImpl("Miguel", 3, new Color(255,200,150), new Color(50,0,0),1.0d,1.0d,0.66d, false),
            new JugadorImpl("Jose", 4, new Color(255,200,150), new Color(50,0,0),0.77d,0.62d,0.61d, false),
            new JugadorImpl("Jesus", 5, new Color(255,200,150), new Color(50,0,0),0.82d,0.64d,0.63d, false),
            new JugadorImpl("Javier", 6, new Color(255,200,150), new Color(50,0,0),1.0d,0.49d,0.7d, false),
            new JugadorImpl("Luis", 7, new Color(255,200,150), new Color(50,0,0),1.0d,0.51d,0.68d, false),
            new JugadorImpl("Alberto", 8, new Color(255,200,150), new Color(50,0,0),1.0d,0.54d,0.69d, false),
            new JugadorImpl("Carlos", 9, new Color(255,200,150), new Color(50,0,0),1.0d,0.76d,0.68d, false),
            new JugadorImpl("Quique", 10, new Color(255,200,150), new Color(50,0,0),1.0d,1.0d,1.0d, false),
            new JugadorImpl("Jorge", 11, new Color(255,200,150), new Color(50,0,0),1.0d,1.0d,1.0d, false)
        };
    }
}

