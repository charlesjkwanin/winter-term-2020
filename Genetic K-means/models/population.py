from sklearn import datasets

from entity import Entity
import numpy as np


class Population:
	def __init__(self, n_clusters: int, population_size: int, X: np.array):
		"""

		:param n_clusters: The number of clusters
		:param population_size: The number of individuals in the population
		:param X: The data we are trying to cluster
		"""
		self.population_size = population_size
		self.population = []
		self.n_clusters = n_clusters
		self.population_fitness = -1
		self.init_population(X)

	def init_population(self, X: np.array):
		for i in range(self.population_size):
			self.population.append(Entity(self.n_clusters, X))

	def get_entities(self):
		return self.population

	def get_size(self):
		return self.population_size

	def set_fitness(self, fitness):
		self.population_fitness = fitness

	def get_pop_fitness(self):
		return self.population_fitness

	def set_entity(self, index: int, entity: Entity):
		self.population[index] = entity

	def get_entity(self, index: int):
		return self.population[index]

	def get_fittest(self):
		self.population.sort(key=lambda entity: entity.get_fitness())
		return self.population[0]

	def __str__(self):
		output = ""
		for entity in self.population:
			output = output + entity.to_string() + "\n"
		return output

	def to_string(self):
		return self.__str__()


def main():
	iris = datasets.load_iris()
	X = iris.data
	y = iris.target

	pop = Population(3, 5, X)

	print("decoded", pop.get_entity(0).decode())

	print(pop)


if __name__ == '__main__':
	main()
