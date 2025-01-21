package io.github.mole.controller.specialities;

import io.github.mole.model.Board;
import io.github.mole.utils.TileType;

import static io.github.mole.utils.TileType.*;

public class AirController {
    Board board;
    int height;
    int width;
    float[][] actualAir;
    float[][] computedAir;
    TileType[][] actualMap;
    public AirController(Board board) {
        this.board = board;
        height = board.getHeight();
        width = board.getWidth();

        actualAir = new float[height][width];
        computedAir = new float[height][width];
        actualMap = new TileType[height][width];

        initializeAir();
    }

    public void initializeAir(){
        for (int x = 0; x < width; x++) {
            actualAir[0][x] = 1;
        }
        for (int y = 1; y < height; y++) {
            for (int x = 0; x < width; x++) {
                actualAir[y][x] = 0;
            }
        }

        getActualMap();
        for (int i=0; i<50; ++i) stepAirSimulation();
        saveAirToBoard();
    }

    public void update(){
        getActualMap();
        getActualAir();
        for (int i=0; i<10; ++i) stepAirSimulation();
        saveAirToBoard();
    }

    private void stepAirSimulation(){
        for (int x = 0; x < width; x++) {
            computedAir[0][x] = 1;
        }
        for (int y = 1; y < height; y++) {
            for (int x = 0; x < width; x++) {
                processTile(x, y);
            }
        }
        float[][] temp = actualAir;
        actualAir = computedAir;
        computedAir = temp;
    }

    private void processTile(int x, int y){
        float sum = actualAir[y][x]*5;
        float dividor = 5;

        float tunnelFlow = 10;

        if (actualMap[y-1][x].equals(AIR) || actualMap[y-1][x].equals(TUNNEL)){
            sum+=actualAir[y-1][x]*tunnelFlow;
            dividor+=tunnelFlow;
        }
        else if (actualMap[y-1][x].equals(DIRT)){
            sum+=actualAir[y-1][x];
            dividor+=1;
        }

        if (y+1 == height){
            dividor+=1;
        }
        else {
            if (actualMap[y+1][x].equals(TUNNEL)){
                sum+=actualAir[y+1][x]*tunnelFlow;
                dividor+=tunnelFlow;
            }
            else if (actualMap[y+1][x].equals(DIRT)){
                sum+=actualAir[y+1][x];
                dividor+=1;
            }
        }

        if (x+1 == width){
            dividor+=1;
        }
        else {
            if (actualMap[y][x+1].equals(TUNNEL)){
                sum+=actualAir[y][x+1]*tunnelFlow;
                dividor+=tunnelFlow;
            }
            else if (actualMap[y][x+1].equals(DIRT)){
                sum+=actualAir[y][x+1];
                dividor+=1;
            }
        }

        if (x == 0){
            dividor+=1;
        }
        else {
            if (actualMap[y][x-1].equals(TUNNEL)){
                sum+=actualAir[y][x-1]*tunnelFlow;
                dividor+=tunnelFlow;
            }
            else if (actualMap[y][x-1].equals(DIRT)){
                sum+=actualAir[y][x-1];
                dividor+=1;
            }
        }

        if (actualMap[y][x].equals(DIRT)) dividor = dividor*1.05f;

        computedAir[y][x] = sum/dividor;
    }

    public float[][] getAirMask(){
        return actualAir;
    }

    private void getActualAir(){
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                actualAir[y][x] = board.getAirLevel(x, y);
            }
        }
    }

    private void getActualMap(){
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                actualMap[y][x] = board.getType(x, y);
            }
        }
    }

    private void saveAirToBoard(){
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                board.setAirLevel(x, y, actualAir[y][x]);
            }
        }
    }
}
