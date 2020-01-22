from typing import List

import numpy as np
from sklearn import datasets


class Entity:

	n_clusters: int
	chromosome: List[int]

	def __init__(self, n_clusters, X: np.ndarray):
		self.n_clusters = n_clusters
		self.chromosome = []
		self.dimensions = X.shape
		self.fitness = 0.0
		self.fill_chromosome()

	def fill_chromosome(self):
		self.chromosome = [0] * self.n_clusters * self.dimensions

	def random_center_choice(self, X: np.ndarray) -> np.ndarray:
		shape = X.shape
		rand_idx = np.random.permutation(shape[0])
		centers = X[rand_idx[:self.n_clusters]]
		return centers

	def calc_fitness(self):
		pass

	def get_fitness(self):
		return self.fitness

	def set_gene(self, idx: int, value: float):
		self.chromosome[idx] = value

	def get_gene(self, idx: int):
		return self.chromosome[idx]

	def chromosome_length(self):
		return len(self.chromosome)

	def set_fitness(self, fitness: float):
		self.fitness = fitness

	def __str__(self):
		return self.chromosome


def main():
	print("Hello World")
	iris = datasets.load_iris()
	X = iris.data
	y = iris.target
	print(y)

	entity = Entity(3, X)
	centers = entity.random_center_choice(X)
	print(entity)
	print("centers", centers)


if __name__ == "main":
	main()

