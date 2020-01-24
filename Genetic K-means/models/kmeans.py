import numpy as np
from numpy.linalg import norm
import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt
from sklearn import datasets
from sklearn.cluster import KMeans


class MyKMeans:

	def __init__(self, n_clusters, random_state=42, init="random", max_iter=300, n_init=10):
		self.error = None
		self.n_clusters = n_clusters
		self.init = init
		self.max_iter = max_iter
		self.n_init = n_init
		self.cluster_centers = []
		self.random_state = random_state
		self.labels = None
		self.distance = None

	def kmeans(self, ):
		pass

	def smart_center_choice(self):
		centers = np.zeros(self.n_clusters)

	def random_center_choice(self, x: np.ndarray) -> np.ndarray:
		shape = x.shape
		rand_idx = np.random.permutation(shape[0])
		centers = x[rand_idx[:self.n_clusters]]
		return centers

	def fit(self, x: np.ndarray):
		self.cluster_centers = self.random_center_choice(x)
		for i in range(self.max_iter):
			old_centers = self.cluster_centers  # update cluster centers
			distance = self.compute_distance(x, old_centers)  # calculate distances from points to cluster centers
			self.labels = self.find_closest_cluster(distance)  # update what clusters each point belongs to
			self.cluster_centers = self.compute_centers(x, self.labels)  # recompute cluster centers
			if np.all(old_centers == self.cluster_centers):  # if cluster centers don't change, break
				break
			self.error = self.compute_sse(x, self.labels, self.cluster_centers)  # calculate the score of the

	def compute_centers(self, x, labels):
		centers = np.zeros((self.n_clusters, x.shape[1]))
		for k in range(self.n_clusters):
			centers[k, :] = np.mean(x[labels == k, :], axis=0)
		return centers

	def compute_distance(self, x, centers):
		distance = np.zeros((x.shape[0], self.n_clusters))
		for i in range(self.n_clusters):
			normal_row = norm(x - centers[i, :], axis=1)
			distance[:, i] = np.square(normal_row)
		self.distance = distance
		return distance

	def find_closest_cluster(self, distance):
		return np.argmin(distance, axis=1)

	def predict(self, x):
		distance = self.compute_distance(x, self.cluster_centers)
		return self.find_closest_cluster(distance)

	def fit_predict(self, x):
		pass

	def fit_transform(self, x):
		pass

	def compute_sse(self, x, labels, centers):
		distance = np.zeros(x.shape[0])
		for i in range(self.n_clusters):
			distance[labels == i] = norm(x[labels == i] - centers[i], axis=1)
		return -np.sum(np.square(distance))

	def score(self):
		return self.error

	def score(self, x):
		return self.compute_sse(x, self.labels, self.cluster_centers)

	def transform(self, x):
		pass

	def set_params(self, **kwargs):
		pass

	def get_distance(self):
		return self.distance


def main():
	my_kmeans = MyKMeans(3)
	iris = datasets.load_iris()
	X = iris.data
	y = iris.target

	my_kmeans.fit(X)
	print("My KMeans score", my_kmeans.score(X))
	print(my_kmeans.find_closest_cluster(my_kmeans.get_distance()))

	# sk_kmeans = KMeans(3)
	# sk_kmeans.fit(X)
	# #print("sklearn KMeans score", sk_kmeans.score(X))


if __name__ == '__main__':
	main()
