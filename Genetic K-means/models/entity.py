from typing import List

import numpy as np
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
		self.dimensions = X.shape
		self.fitness = 0.0
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
		rand_idx = np.random.permutation(shape[0])
		centers = X[rand_idx[:self.n_clusters]]
		for k in centers:
			for j in k:
				self.chromosome.append(j)

	def calc_fitness(self):
		"""
		Calculate the fitness for this specific entity
		"""
		pass

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


# def main():
# 	iris = datasets.load_iris()
# 	X = iris.data
# 	y = iris.target
#
# 	entity = Entity(3, X)
# 	print("entity", entity)
#
#
# if __name__ == '__main__':
# 	main()
