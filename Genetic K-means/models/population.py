from sklearn import datasets

from .entity import Entity
import numpy as np


class Population:
	def __init__(self, n_clusters: int, population_size: int, X: np.array):
		self.population_size = population_size
		self.population = []
		self.init_population(X)
		self.n_clusters = n_clusters
		self.population_fitness = -1
		self.init_population(X)

	def init_population(self, X: np.array):
		for i in range(self.population_size):
			self.set_entity(i, Entity(self.n_clusters, X))

	def get_entities(self):
		return self.population

	def get_size(self):
		return self.population_size

	def get_pop_fitness(self):
		return self.population_fitness

	def set_entity(self, index: int, entity: Entity):
		self.population[index] = entity

	def get_entity(self, index: int):
		return self.population[index]

	def __str__(self):
		output = ""
		for entity in self.population:
			output = output + entity + "\n"


def main():
	iris = datasets.load_iris()
	X = iris.data
	y = iris.target

	pop = Population(5, X)

	print(pop)


if __name__ == '__main__':
	main()
