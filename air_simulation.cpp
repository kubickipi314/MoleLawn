#include<iostream>
using namespace std;

int n = 15;

char tile_table[15][15];
float air_table[15][15];

float temp_air[15][15];

int tunnel_flow = 10;

void initialize_tables(){

    for (int i=0; i<n; ++i){
        tile_table[0][i] = ' ';
        air_table[0][i] = 99;
    }
    for (int i=1; i<n; ++i){
        for (int j=0; j<n; ++j){
            tile_table[i][j] = 'D';
            air_table[i][j] = 0;
        }
    }

    tile_table[1][4] = ' ';
    tile_table[2][4] = ' ';
    tile_table[3][4] = ' ';
    tile_table[4][4] = ' ';
    tile_table[5][4] = ' ';
    tile_table[6][4] = ' ';
    tile_table[7][4] = ' ';
    tile_table[7][5] = ' ';
    tile_table[7][6] = ' ';
    tile_table[7][7] = ' ';

}

void print_tables(){
    for (int i=0; i<n; ++i){
        for (int j=0; j<n; ++j){
            int c = air_table[i][j];
            if (tile_table[i][j] == 'D')
                std::cout << "\033[48;2;"<<c+50<<";"<<c+10<<";"<<c<<"m";
            else 
                std::cout << "\033[48;2;"<<c<<";"<<c+20<<";"<<c+50<<"m";
            cout<<"  ";
            std::cout << "\033[0m";
        }
        // cout<<" |  ";
        // for (int j=0; j<n; ++j){
        //     if (air_table[i][j]<10) cout<<" ";
        //     cout<<air_table[i][j]<<" ";
        // }
        cout<<endl;
    }
}

void make_step(){
    for (int i=1; i<n; ++i){
        for (int j=0; j<n; ++j){
            float sum = air_table[i][j]*5;
            float dividor = 5;

            if (tile_table[i-1][j] == ' '){
                sum+=air_table[i-1][j]*tunnel_flow;
                dividor+=tunnel_flow;
            }
            else if (tile_table[i][j]=='D'){
                sum+=air_table[i-1][j];
                dividor+=1;
            }

            if (i+1 == n){
                dividor+=1;
            }
            else {
                if (tile_table[i+1][j] == ' '){
                    sum+=air_table[i+1][j]*tunnel_flow;
                    dividor+=tunnel_flow;
                }
                else if (tile_table[i][j]=='D'){
                    sum+=air_table[i+1][j];
                    dividor+=1;
                }
            }

            if (j+1 == n){
                dividor+=0;
            }
            else {
                if (tile_table[i][j+1] == ' '){
                    sum+=air_table[i][j+1]*tunnel_flow;
                    dividor+=tunnel_flow;
                }
                else if (tile_table[i][j]=='D') {
                    sum+=air_table[i][j+1];
                    dividor+=1;
                }
            }

            if (j == 0){
                dividor+=0;
            }
            else {
                if (tile_table[i][j-1] == ' '){
                    sum+=air_table[i][j-1]*tunnel_flow;
                    dividor+=tunnel_flow;
                }
                else if (tile_table[i][j]=='D'){
                    sum+=air_table[i][j-1];
                    dividor+=1;
                }
            }

            if (tile_table[i][j]=='D') dividor = dividor*1.05;

            temp_air[i][j] = sum/dividor;
        }
    }
    for (int i=1; i<n; ++i){
        for (int j=0; j<n; ++j){
            air_table[i][j] = temp_air[i][j];
        }
    }
}


int main(){

    initialize_tables();
    print_tables();


    char a;
    while(cin>>a){
        if (a == 'b') {
            tile_table[1][4] = 'D';
            tile_table[2][4] = 'D';
        }
        else if (a == 't') {
            tile_table[7][8] = ' ';
            tile_table[7][9] = ' ';
            tile_table[8][9] = ' ';
            tile_table[9][9] = ' ';
            tile_table[10][9] = ' ';
            tile_table[11][9] = ' ';
            tile_table[12][9] = ' ';
            tile_table[12][8] = ' ';
            tile_table[13][8] = ' ';
            tile_table[13][7] = ' ';
        }
        else if (a == 'k') {
            tile_table[13][6] = ' ';
            tile_table[13][5] = ' ';
            tile_table[13][4] = ' ';
            tile_table[12][4] = ' ';
            tile_table[11][4] = ' ';
            tile_table[11][3] = ' ';
            tile_table[10][3] = ' ';
            tile_table[10][2] = ' ';
            tile_table[10][1] = ' ';
            tile_table[9][1] = ' ';

        } else {
            for (int i=0; i<20; ++i)
                make_step();
        }
        system("clear");
        print_tables();
    }

    return 0;
}