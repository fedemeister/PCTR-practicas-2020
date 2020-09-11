//
// Created by fedem on 2020-08-11.
//

#include<iostream>
#include<vector>
#include<thread>


using namespace std;
typedef std::vector<std::vector<int> > matrix;

matrix getMatrixM();

matrix getMatrixExample();

matrix getMatrixEnfoque();

matrix getMatrixRealBordes();

matrix getMatrixDetecBordes();

matrix getMatrixSobel();

matrix getMatrixSharpen();

int showMenuAndChooseOption(matrix &M, const matrix &example, const matrix &enfoque, const matrix &realbordes,
                            const matrix &detecbordes, const matrix &sobel, const matrix &sharpen, int op,
                            matrix &kernel, matrix &conv);


ostream &operator<<(ostream &o, const matrix &v) {
    for (int i = 0; i < v.size(); ++i) {
        for (int j = 0; j < v.size(); ++j) {
            cout << v[i][j] << " ";
        }
        cout << endl;
    }
    o << endl << endl;
    return o;
}

int element(matrix &m, int i, int j) {
    return i < 0 || j < 0 || i > m.size() - 1 || j > m.size() - 1 ? 0 : m[i][j];
}

int validResult(int n) {
    if (n < 0)
        return 0;
    if (n > 255)
        return 255;
    else
        return n;
}

int calc(matrix &m, matrix &c, int x, int y) {
    int suma = 0;
    int fil = 0;
    int col;
    for (int i = x - 1; i <= x + 1; i++) {
        col = 0;
        for (int j = y - 1; j <= y + 1; j++) {
            suma += element(m, i, j) * c[fil][col];
            col++;
        }
        fil++;
    }
    return suma;
}


void convolucion(matrix &M, matrix &C, matrix &res, int ini,
                 int end)
{
    int sizeM = M.size();

    for (int i = ini; i < end; i++) {
        res[i].resize(M.size());
        for (int j = 0; j < sizeM; j++) {
            int aux = calc(M, C, i, j);
            res[i][j] = validResult(aux);

        }
    }
}


void llamada(matrix &M, matrix &C, matrix &convo) {
    int msize = M.size();
    int nhilos = 4;
    vector<thread> hilos(4);
    int ventana = msize / nhilos;
    for (int i = 0; i < nhilos - 1; i++) {
        hilos[i] = thread(convolucion, ref(M), ref(C), ref(convo), i * ventana, (i + 1) * ventana); //POR AQUI VOY
    }

    hilos[nhilos - 1] = thread(convolucion, ref(M), ref(C), ref(convo), (nhilos - 1) * ventana, msize);

    for (int i = 0; i < nhilos; i++)
        hilos[i].join();
}


int main(int argc, char const *argv[]) {
    matrix M = getMatrixM();

    matrix example = getMatrixExample();

    matrix enfoque = getMatrixEnfoque();

    matrix realbordes = getMatrixRealBordes();

    matrix detecbordes = getMatrixDetecBordes();

    matrix sobel = getMatrixSobel();

    matrix sharpen = getMatrixSharpen();

    int op = 1;
    matrix kernel;
    matrix conv = M;
    do {
        op = showMenuAndChooseOption(M, example, enfoque, realbordes, detecbordes, sobel, sharpen, op, kernel, conv);
    } while (op != 0);


    return 0;
}

int showMenuAndChooseOption(matrix &M, const matrix &example, const matrix &enfoque, const matrix &realbordes,
                            const matrix &detecbordes, const matrix &sobel, const matrix &sharpen, int op,
                            matrix &kernel, matrix &conv) {
    cout << "Introduce una opcion: " << endl << endl;
    cout << "0-Salir" << endl;
    cout << "1-Ejemplo" << endl;
    cout << "2-Enfoque" << endl;
    cout << "3-Detectar bordes" << endl;
    cout << "4-Realzar bordes" << endl;
    cout << "5-Sobel" << endl;
    cout << "6-Sharpen" << endl;
    cin >> op;
    switch (op) {
        case 0:
            break;
        case 1:
            cout << "Matriz del ejemplo" << endl;
            kernel = example;
            break;
        case 2:
            cout << "Matriz para enfoque" << endl;
            kernel = enfoque;
            break;
        case 3:
            cout << "Matriz para detectar bordes" << endl;
            kernel = detecbordes;
            break;
        case 4:
            cout << "Matriz para realzar bordes" << endl;
            kernel = realbordes;
            break;
        case 5:
            cout << "Matriz Sobel" << endl;
            kernel = sobel;
            break;
        case 6:
            cout << "Matriz Sharpen" << endl;
            kernel = sharpen;
            break;
    }
    cout << endl;

    if (op != 0) {
        cout << "Matriz Original: " << endl;
        cout << M;
        cout << "Matriz Kernel:" << endl;
        cout << kernel;
        llamada(M, kernel, conv);
        cout << "Matriz Convolucionada:" << endl;
        cout << conv;
        cout << endl << endl;
    }
    return op;
}

matrix getMatrixSharpen() {
    matrix sharpen =
            {
                    {1,  -2, 1},
                    {-2, 5,  -2},
                    {1,  -2, 1}
            };
    return sharpen;
}

matrix getMatrixSobel() {
    matrix sobel =
            {
                    {-1, 0, 1},
                    {-2, 0, 2},
                    {-1, 0, 1}
            };
    return sobel;
}

matrix getMatrixDetecBordes() {
    matrix detecbordes =
            {
                    {0, 1,  0},
                    {1, -4, 1},
                    {0, 1,  0}
            };
    return detecbordes;
}

matrix getMatrixRealBordes() {
    matrix realbordes =
            {
                    {0,  0, 0},
                    {-1, 1, 0},
                    {0,  0, 0}
            };
    return realbordes;
}

matrix getMatrixEnfoque() {
    matrix enfoque =
            {
                    {0,  -1, 0},
                    {-1, 5,  -1},
                    {0,  -1, 0}
            };
    return enfoque;
}

matrix getMatrixExample() {
    matrix example =
            {
                    {-2, -1, 0},
                    {-1, 1,  1},
                    {0,  1,  2}
            };
    return example;
}

matrix getMatrixM() {
    matrix M =
            {
                    {35, 40, 41, 45, 50},
                    {40, 40, 42, 46, 52},
                    {42, 46, 50, 55, 55},
                    {48, 52, 56, 58, 60},
                    {56, 60, 65, 70, 75}
            };
    return M;
}

