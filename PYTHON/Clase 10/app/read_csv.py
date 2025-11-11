import pandas as pd

def read_csv(path):
    data = pd.read_csv(path)
    data = data.to_dict(orient='records')
    return data

if __name__ == '__main__':
    data = read_csv('./app/data.csv')
    print(data[0])