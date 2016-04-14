import numpy as np
from sklearn.feature_extraction import DictVectorizer
# from pyfm import pylibfm
from pyfm import pylibfm

# Read in data
def loadData(filename,path="data/"):
    data = []
    y = []
    users=set()
    items=set()
    with open(path+filename) as f:
        count = 0
        for line in f:
            count +=1
            (user,movieid,rating)=line.strip().split("*:=")
            data.append({ "user_id": str(user), "movie_id": str(movieid)})
            y.append(float(rating))
            users.add(user)
            items.add(movieid)
            # print count
    # print items
    return (data, np.array(y), users, items)

(train_data, y_train, train_users, train_items) = loadData("trainWithoutTP.txt")
(test_data, y_test, test_users, test_items) = loadData("testWithoutTP.txt")
v = DictVectorizer()
X_train = v.fit_transform(train_data)
X_test = v.transform(test_data)

# Build and train a Factorization Machine
fm = pylibfm.FM(num_factors=5, num_iter=200, verbose=True, task="regression", initial_learning_rate=0.00005, learning_rate_schedule="optimal")

fm.fit(X_train,y_train)
# fm.predict(v.transform({"user_id": "2", "movie_id": "bbb", "timestamp": 24}))
preds = fm.predict(X_test)
from sklearn.metrics import mean_squared_error
print("FM MSE: %.10f" % mean_squared_error(y_test,preds))