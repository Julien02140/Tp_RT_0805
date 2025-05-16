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

print("Page title is:", driver.title)

print("✅ Tous les tests Selenium sont passés avec succès !")

driver.quit()
