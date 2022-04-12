package ags.ics.msd;

import java.util.ArrayList;

public class Point {

    public ArrayList<Point> neumann_neighbors;
    public ArrayList<Point> moore_neighbors;
    public static Integer []types ={0,1,2,3};
    public int type;
    public int staticField;
    public boolean isPedestrian;

    public Point() {
        type=0;
        staticField = 100000;
        neumann_neighbors= new ArrayList<Point>();
        moore_neighbors = new ArrayList<Point>();
    }

    public void clear() {
        staticField = 100000;

    }

    public boolean calcStaticField() {
        int minimum = this.staticField;
        for (Point nei : this.moore_neighbors)
        {
            if (nei.staticField + 1< minimum)
            {
                minimum = nei.staticField + 1;
            }
        }
        if (minimum != this.staticField)
        {
            this.staticField = minimum;
            return true;
        }
        return false;
    }

    public void move(){
    }

    public void setStaticField()
    {
        this.staticField = 0;
    }

    public int getType()
    {
        return this.type;
    }

    public void addMooreNeighbor(Point nei) {
        moore_neighbors.add(nei);
    }


    public void addNeumannNeighbor(Point nei) {
        neumann_neighbors.add(nei);
    }
}

