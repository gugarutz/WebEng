<%@ page import="at.ac.tuwien.big.we15.lab2.api.impl.model.impl.CategoryBean" %>
<%@ page import="at.ac.tuwien.big.we15.lab2.api.SelectableQuestion" %>
<jsp:useBean id="stats" class="at.ac.tuwien.big.we15.lab2.api.impl.model.impl.PlayerStats" scope="session" />
<jsp:useBean id="info" class="at.ac.tuwien.big.we15.lab2.api.impl.model.impl.PlayerInfo" scope="session" />
<jsp:useBean id="categories" class="at.ac.tuwien.big.we15.lab2.api.impl.model.impl.CategoryListBean" scope="session" />

<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="de" lang="de">
<head>
   <meta charset="utf-8"/>
   <meta name="viewport" content="width=device-width, initial-scale=1.0" />
   <title>Business Informatics Group Jeopardy! - Fragenauswahl</title>
   <link rel="stylesheet" type="text/css" href="style/base.css" />
   <link rel="stylesheet" type="text/css" href="style/screen.css" />
   <script src="js/jquery.js" type="text/javascript"></script>
   <script src="js/framework.js" type="text/javascript"></script>
</head>
<body id="selection-page">
<a class="accessibility" href="#question-selection">Zur Fragenauswahl springen</a>
<!-- Header -->
<header role="banner" aria-labelledby="bannerheading">
   <h1 id="bannerheading">
      <span class="accessibility">Business Informatics Group </span><span class="gametitle">Jeopardy!</span>
   </h1>
</header>

<!-- Navigation -->
<nav role="navigation" aria-labelledby="navheading">
   <h2 id="navheading" class="accessibility">Navigation</h2>
   <ul>
      <li><a class="orangelink navigationlink" id="logoutlink" title="Klicke hier um dich abzumelden" href="/logout" accesskey="l">Abmelden</a></li>
   </ul>
</nav>

<!-- Content -->
<div role="main">
   <!-- info -->
   <section id="gameinfo" aria-labelledby="gameinfoinfoheading">
      <h2 id="gameinfoinfoheading" class="accessibility">Spielinformationen</h2>
      <section id="firstplayer" class="playerinfo leader" aria-labelledby="firstplayerheading">
         <h3 id="firstplayerheading" class="accessibility">F&uuml;hrender Spieler</h3>
         <img class="avatar" src="img/avatar/<%= stats.getHuman().getAvatar().getImageHead() %>" alt="Spieler-Avatar <%= stats.getHuman().getAvatar().getName() %>" />
         <table>
            <tr>
            <th class="accessibility">Spielername</th>
               <td class="playername"><%= stats.getHuman().getName() %> (Du)</td>
            </tr>
            <tr>
               <th class="accessibility">Spielerpunkte</th>
               <td class="playerpoints"><%= stats.getHuman().getMoney() %> &euro;</td>
            </tr>
         </table>
      </section>
      <section id="secondplayer" class="playerinfo" aria-labelledby="secondplayerheading">
         <h3 id="secondplayerheading" class="accessibility">Zweiter Spieler</h3>
         <img class="avatar" src="img/avatar/<%= stats.getEnemy().getAvatar().getImageHead()%>" alt="Spieler-Avatar <%= stats.getEnemy().getAvatar().getName() %>" />
         <table>
            <tr>
               <th class="accessibility">Spielername</th>
               <td class="playername"><%= stats.getEnemy().getAvatar().getName()%></td>
            </tr>
            <tr>
                <th class="accessibility">Spielerpunkte</th>
               <td class="playerpoints"><%= stats.getEnemy().getMoney() %> &euro;</td>
            </tr>
         </table>
      </section>
      <p id="round">Fragen: <%= stats.getAskedQuestions() %> / 10</p>
   </section>

   <!-- Question -->
   <section id="question-selection" aria-labelledby="questionheading">
      <h2 id="questionheading" class="black accessibility">Jeopardy</h2>
      <p class="user-info <%= info.getHumanInfo().isCorrect() ? "positive-change" : "negative-change" %>"><%= info.getHumanInfo() %></p>
      <p class="user-info <%= info.getEnemyInfo().isCorrect() ? "positive-change" : "negative-change" %>"><%= info.getEnemyInfo() %></p>
      <p class="user-info"><%= info.getEnemyChosenQuestion() %></p>
      <form id="questionform" action="jeopardy" method="post">
         <fieldset>
            <legend class="accessibility">Fragenauswahl</legend>
             <% for(CategoryBean c : categories.getCategories()) { %>
                 <section class="questioncategory" aria-labelledby="<%= c.getName() %>">
                     <h3 id="<%= c.getName() %>" class="tile category-title"><span class="accessibility">Kategorie: </span><%= c.getName() %></h3>
                     <ol class="category_questions">
                         <% for(SelectableQuestion q : c.getQuestions()) { %>
                            <li><input name="question_selection" id="<%= String.format("question_%s", q.getQuestion().getId()) %>" value="<%= q.getQuestion().getId() %>" type="radio" <%= q.isDisabled() ? "disabled" : "" %> /><label class="tile clickable" for="<%= String.format("question_%s", q.getQuestion().getId()) %>">&euro; <%= q.getQuestion().getValue() %></label></li>
                         <% } %>
                     </ol>
                 </section>
             <% } %>
         </fieldset>
         <input type="hidden" name="action" value="jeopardy" />
         <input class="greenlink formlink clickable" name="question_submit" id="next" type="submit" value="w&auml;hlen" accesskey="s" />
      </form>
   </section>

   <section id="lastgame" aria-labelledby="lastgameheading">
      <h2 id="lastgameheading" class="accessibility">Letztes Spielinfo</h2>
      <p>Letztes Spiel: Nie</p>
   </section>
</div>

<!-- footer -->
<footer role="contentinfo">&copy; 2015 BIG Jeopardy!</footer>

<script type="text/javascript">
   //<![CDATA[

   // initialize time
   $(document).ready(function() {
      // set last game
      if(supportsLocalStorage()) {
         var lastGameMillis = parseInt(localStorage['lastGame'])
         if(!isNaN(parseInt(localStorage['lastGame']))){
            var lastGame = new Date(lastGameMillis);
            $("#lastgame p").replaceWith('<p>Letztes Spiel: <time datetime="'
            + lastGame.toUTCString()
            + '">'
            + lastGame.toLocaleString()
            + '</time></p>')
         }
      }
   });
   //]]>
</script>
</body>
</html>
