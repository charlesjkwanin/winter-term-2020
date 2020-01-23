from typing import List

import numpy as np
from numpy.linalg import norm
from sklearn import datasets

class Entity:

	n_clusters: int
	chromosome: List[int]

	def __init__(self, n_clusters, X: np.ndarray):
		"""
		:param n_clusters: Number of clusters
		:param X: Data set we are clustering. Has to be a numpy array
		"""
		self.n_clusters = n_clusters
		self.chromosome = []
		self.shape = X.shape
		self.fitness = 0.0
		self.labels = None
		self.centers = None
		self.fill_chromosome(X)

	def fill_chromosome(self, X: np.ndarray):
		"""
		Fill the chromosome with randomly chosen points from the original
		data set.
		The length of chromosome is N*K, where N is the number of dimensions of
		the data set, and K is the number of cluster centers
		:param X:  Data set we are clustering. Has to be a numpy ar
		"""
		shape = X.shape
		self.data = X
		rand_idx = np.random.permutation(shape[0])
		centers = X[rand_idx[:self.n_clusters]]
		self.centers = centers
		self.chromosome = self.encode(centers)

	def decode(self):
		arr = np.array(self.chromosome)
		return arr.reshape(self.n_clusters, self.shape[1])

	def encode(self, center: np.ndarray):
		chromosome_arr = []
		for k in center:
			for j in k:
				chromosome_arr.append(j)
		return chromosome_arr

	def calc_fitness(self):
		"""
		Calculate the fitness for this specific entity
		"""
		distance = self.compute_distance(self.data, self.centers)  # calculate distances from points to cluster centers
		self.labels = self.find_closest_cluster(distance)  # update what clusters each point belongs to
		self.centers = self.compute_centers(self.data, self.labels)  # recompute cluster centers
		self.chromosome = self.encode(self.centers)
		fitness = self.compute_sse(self.data, self.labels, self.centers)
		self.fitness = fitness
		return fitness

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
		return distance

	def find_closest_cluster(self, distance):
		return np.argmin(distance, axis=1)

	def compute_sse(self, x, labels, centers):
		distance = np.zeros(x.shape[0])
		for i in range(self.n_clusters):
			distance[labels == i] = norm(x[labels == i] - centers[i], axis=1)
		return np.sum(np.square(distance))

	def get_fitness(self):
		"""
		:rtype: Double
		:return: The fitness of this entity
		"""
		return self.fitness

	def set_gene(self, idx: int, value: float):
		"""
		Set the given value as the new value at this index
		:param idx: Index at which to set gene
		:param value: Value to insert at this location in the gene
		"""
		self.chromosome[idx] = value

	def get_gene(self, idx: int):
		return self.chromosome[idx]

	def chromosome_length(self):
		return len(self.chromosome)

	def set_fitness(self, fitness: float):
		self.fitness = fitness

	def __str__(self):
		if self.chromosome is None:
			return ""
		output = str(self.chromosome[0])
		for i in self.chromosome[1:]:
			output = output + " " + str(i)
		return output

	def to_string(self):
		return self.__str__()

