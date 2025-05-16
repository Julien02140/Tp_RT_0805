from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options

# Options pour tourner sans interface graphique
chrome_options = Options()
chrome_options.add_argument("--headless")
chrome_options.add_argument("--no-sandbox")
chrome_options.add_argument("--disable-dev-shm-usage")

# Lancer Chrome avec le bon driver en mode headless
driver = webdriver.Chrome(
    service=Service("/usr/local/bin/chromedriver"),  # Chemin vers chromedriver
    options=chrome_options
)

# Aller sur ton URL
driver.get("http://10.11.17.50:8888/jakartaee-hello-world")

# Vérifier le texte des titres
h1 = driver.find_element(By.TAG_NAME, "h1")
assert h1.text == "Bienvenue sur MyVideo"

h2 = driver.find_element(By.TAG_NAME, "h2")
assert h2.text == "Rejoignez-nous dès maintenant"

# Vérifier les boutons
btn_connexion = driver.find_element(By.LINK_TEXT, "Se connecter")
btn_inscription = driver.find_element(By.LINK_TEXT, "S'inscrire")

assert "btn-primary" in btn_connexion.get_attribute("class")
assert "btn-secondary" in btn_inscription.get_attribute("class")

assert btn_connexion.get_attribute("href").endswith("login.html")
assert btn_inscription.get_attribute("href").endswith("register.html")

print("✅ Tous les tests Selenium sont passés avec succès !")

driver.quit()
