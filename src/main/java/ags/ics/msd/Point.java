package ags.ics.msd;

import java.util.ArrayList;

public class Point {

    public ArrayList<Point> neumann_neighbors;
    public ArrayList<Point> moore_neighbors;
    public static Integer []types ={0,1,2,3};
    public int type;
    public int staticField;
    public boolean isPedestrian;
    public int neighbourhood;
    boolean blocked = false;

    public Point() {
        type=0;
        staticField = 100000;
        neumann_neighbors= new ArrayList<>();
        moore_neighbors = new ArrayList<>();

    }

    public void clear() {
        staticField = 100000;

    }

    public boolean calcStaticField() {
        int minimum = this.staticField;
        if (this.neighbourhood == 0) {
            for (Point nei : this.moore_neighbors) {
                if (nei.staticField + 1 < minimum) {
                    minimum = nei.staticField + 1;
                }
            }
            if (minimum != this.staticField) {
                this.staticField = minimum;
                return true;
            }
            return false;
        }
        else
        {
            for (Point nei : this.neumann_neighbors) {
                if (nei.staticField + 1 < minimum) {
                    minimum = nei.staticField + 1;
                }
            }
            if (minimum != this.staticField) {
                this.staticField = minimum;
                return true;
            }
            return false;
        }
    }

    public void move()
    {
        if (this.isPedestrian == true)
        {
            int min = 100001;
            Point new_pos = null;
            if (this.neighbourhood == 0) {
                for (Point nei : this.moore_neighbors) {
                    if ((nei.type == 0 || nei.type == 2) && nei.staticField < min) {
                        min = nei.staticField;
                        new_pos = nei;
                    }
                }
                if (new_pos != null) {
                    this.type = 0;
                    if (new_pos.type == 0) {
                        new_pos.isPedestrian = true;
                        new_pos.type = 3;
                    }
                    new_pos.blocked = true;
                    this.isPedestrian = false;
                }
                this.blocked = true;
            }
            else
            {
                for (Point nei : this.neumann_neighbors) {
                    if ((nei.type == 0 || nei.type == 2) && nei.staticField < min) {
                        min = nei.staticField;
                        new_pos = nei;
                    }
                }
                if (new_pos != null) {
                    this.type = 0;
                    if (new_pos.type == 0)
                    {
                        new_pos.isPedestrian = true;
                        new_pos.type = 3;
                    }

                    new_pos.blocked = true;
                    this.isPedestrian = false;
                }
                this.blocked = true;
            }
        }
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

