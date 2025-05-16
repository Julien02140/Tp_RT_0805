from locust import HttpUser, task, between

class WebsiteUser(HttpUser):
    wait_time = between(1, 5)  # attente entre 1 et 5 secondes entre les tâches

    @task
    def index(self):
        self.client.get("/jakartaee-hello-world")

    @task(3)  # cette tâche a 3 fois plus de chances d’être exécutée
    def about(self):
        self.client.get("/jakartaee-hello-world/login.html")  # tester une autre page, adapte selon ton appli
