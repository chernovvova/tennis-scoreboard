<%--
  Created by IntelliJ IDEA.
  User: vova
  Date: 17.08.2024
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Табло текущего матча</title>
    <link href="${pageContext.request.contextPath}/style/style.css" rel="stylesheet">
</head>
<body>
<header>
    <div class="container bc-lightgray">
        <div class="table-header p-20">
            <span class="h1">Табло текущего теннисного матча</span>
        </div>
    </div>
</header>
<section>
    <div class="container">
        <div class="table-general">
            <div>
                <div class="table-title">
                    <p class="text-title">Match №</p>
                </div>
            </div>
            <div>
                <div class="table-sign">
                    <div class="col-prev-sets ml-6 mr-6">
                        <p class="text-sign">previous sets</p>
                    </div>
                    <div class="col-player">
                        <p class="text-sign">player</p>
                    </div>
                    <div class="col-sets mr-6 ml-6">
                        <p class="text-sign">sets</p>
                    </div>
                    <div class="col-points mr-6 ml-6">
                        <p class="text-sign">points</p>
                    </div>
                    <div class="col-but">
                        <p class="text-sign"></p>
                    </div>
                </div>
            </div>
            <div>
                <div class="table-score">
                    <div class="col-prev-sets ml-6 mr-6">
                        <div class="col-score-set">
                            <c:if test="${match.matchScore.getSetResults(1, 0) == -1}">
                                <p class="text-score" style="color: black">.</p>
                            </c:if>
                            <c:if test="${match.matchScore.getSetResults(1, 0) != -1}">
                                <p class="text-score"><c:out
                                        value="${match.matchScore.getSetResults(1, 0)}"></c:out></p>
                            </c:if>
                        </div>
                        <div class="col-score-set">
                            <c:if test="${match.matchScore.getSetResults(2, 0) == -1}">
                                <p class="text-score" style="color: black">.</p>
                            </c:if>
                            <c:if test="${match.matchScore.getSetResults(2, 0) != -1}">
                                <p class="text-score"><c:out
                                        value="${match.matchScore.getSetResults(2, 0)}"></c:out></p>
                            </c:if>
                        </div>
                        <div class="col-score-set">
                            <c:if test="${match.matchScore.getSetResults(3, 0) == -1}">
                                <p class="text-score" style="color: black">.</p>
                            </c:if>
                            <c:if test="${match.matchScore.getSetResults(3, 0) != -1}">
                                <p class="text-score"><c:out
                                        value="${match.matchScore.getSetResults(3, 0)}"></c:out></p>
                            </c:if>
                        </div>
                        <div class="col-score-set">
                            <c:if test="${match.matchScore.getSetResults(4, 0) == -1}">
                                <p class="text-score" style="color: black">.</p>
                            </c:if>
                            <c:if test="${match.matchScore.getSetResults(4, 0) != -1}">
                                <p class="text-score"><c:out
                                        value="${match.matchScore.getSetResults(4, 0)}"></c:out></p>
                            </c:if>
                        </div>
                    </div>
                    <div class="col-player">
                        <div class="player">
                            <p class="text-player">${match.player1.name}</p>
                        </div>
                    </div>
                    <div class="col-sets mr-6 ml-6">
                        <div class="col-score-set">
                            <c:if test="${match.matchScore.getPlayerScore(0) != match.matchScore.countSetsForWin
                                    and match.matchScore.getPlayerScore(1) != match.matchScore.countSetsForWin}">
                                <p class="text-score">${match.matchScore.currentSetScore.getPlayerScore(0)}</p>
                            </c:if>
                            <c:if test="${match.matchScore.getPlayerScore(0) + match.matchScore.getPlayerScore(1) == 5}">
                                <p class="text-score">${match.matchScore.currentSetScore.getPlayerScore(0)}</p>
                            </c:if>
                            <c:if test="${(match.matchScore.getPlayerScore(0) == match.matchScore.countSetsForWin
                                    or match.matchScore.getPlayerScore(1) == match.matchScore.countSetsForWin)
                                    and (match.matchScore.getPlayerScore(0) + match.matchScore.getPlayerScore(1)) != 5}">
                                <p class="text-score">0</p>
                            </c:if>
                        </div>
                    </div>
                    <div class="col-points mr-6 ml-6">
                        <div class="col-score-points">
                            <p class="text-score">${match.matchScore.getCurrentGameScore(0)}</p>
                        </div>
                    </div>
                    <div class="col-but">
                        <div class="btn-wrapper">
                            <form method="post">
                                <c:if test="${match.matchScore.getPlayerScore(0) != match.matchScore.countSetsForWin
                                    and match.matchScore.getPlayerScore(1) != match.matchScore.countSetsForWin}">
                                    <button class="btn" name="player-1" value="player-1-win">Take score #1</button>
                                </c:if>
                            </form>
                        </div>
                    </div>

                </div>
            </div>
            <div class="empty"></div>
            <div>
                <div class="table-score">
                    <div class="col-prev-sets ml-6 mr-6">
                        <div class="col-score-set">
                            <c:if test="${match.matchScore.getSetResults(1, 1) == -1}">
                                <p class="text-score" style="color: black">.</p>
                            </c:if>
                            <c:if test="${match.matchScore.getSetResults(1, 1) != -1}">
                                <p class="text-score"><c:out
                                        value="${match.matchScore.getSetResults(1, 1)}"></c:out></p>
                            </c:if>
                        </div>
                        <div class="col-score-set">
                            <c:if test="${match.matchScore.getSetResults(2, 1) == -1}">
                                <p class="text-score" style="color: black">.</p>
                            </c:if>
                            <c:if test="${match.matchScore.getSetResults(2, 1) != -1}">
                                <p class="text-score"><c:out
                                        value="${match.matchScore.getSetResults(2, 1)}"></c:out></p>
                            </c:if>
                        </div>
                        <div class="col-score-set">
                            <c:if test="${match.matchScore.getSetResults(3, 1) == -1}">
                                <p class="text-score" style="color: black">.</p>
                            </c:if>
                            <c:if test="${match.matchScore.getSetResults(3, 1) != -1}">
                                <p class="text-score"><c:out
                                        value="${match.matchScore.getSetResults(3, 1)}"></c:out></p>
                            </c:if>
                        </div>
                        <div class="col-score-set">
                            <c:if test="${match.matchScore.getSetResults(4, 1) == -1}">
                                <p class="text-score" style="color: black">.</p>
                            </c:if>
                            <c:if test="${match.matchScore.getSetResults(4, 1) != -1}">
                                <p class="text-score"><c:out
                                        value="${match.matchScore.getSetResults(4, 1)}"></c:out></p>
                            </c:if>
                        </div>
                    </div>
                    <div class="col-player">
                        <div class="player">
                            <p class="text-player">${match.player2.name}</p>
                        </div>
                    </div>
                    <div class="col-sets mr-6 ml-6">
                        <div class="col-score-set">
                            <c:if test="${match.matchScore.getPlayerScore(0) != match.matchScore.countSetsForWin
                                    and match.matchScore.getPlayerScore(1) != match.matchScore.countSetsForWin}">
                                <p class="text-score">${match.matchScore.currentSetScore.getPlayerScore(1)}</p>
                            </c:if>
                            <c:if test="${match.matchScore.getPlayerScore(0) + match.matchScore.getPlayerScore(1) == 5}">
                                <p class="text-score">${match.matchScore.currentSetScore.getPlayerScore(1)}</p>
                            </c:if>
                            <c:if test="${(match.matchScore.getPlayerScore(0) == match.matchScore.countSetsForWin
                                    or match.matchScore.getPlayerScore(1) == match.matchScore.countSetsForWin)
                                    and (match.matchScore.getPlayerScore(0) + match.matchScore.getPlayerScore(1)) != 5}">
                                <p class="text-score">0</p>
                            </c:if>
                        </div>
                    </div>
                    <div class="col-points mr-6 ml-6">
                        <div class="col-score-points">
                            <p class="text-score">${match.matchScore.getCurrentGameScore(1)}</p>
                        </div>
                    </div>
                    <div class="col-but">
                        <div class="btn-wrapper">
                            <form method="post">
                                <c:if test="${match.matchScore.getPlayerScore(0) != match.matchScore.countSetsForWin
                                    and match.matchScore.getPlayerScore(1) != match.matchScore.countSetsForWin}">
                                    <button class="btn" name="player-2" value="player-2-win">Take score #2</button>
                                </c:if>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <c:if test="${match.matchScore.getPlayerScore(0) == match.matchScore.countSetsForWin
                    or match.matchScore.getPlayerScore(1) == match.matchScore.countSetsForWin}">
                <div>
                    <div class="table-result-title">
                        <p class="result-title">winner</p>
                    </div>
                    <div class="table-result">
                        <p style="font-size: 18px; font-weight: bold">${match.winner.name}</p>
                    </div>
                    <div style="background-color: lightgray; margin-bottom: 0px; margin-top: -10px; padding-bottom: 15px;">

                        <form action="end-match" method="post">
                            <input type="hidden" value="${match.id}" name="uuid">
                            <button type="submit" class="card_button" style="font-size: 14px;">Continue</button>
                        </form>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
</section>
</body>
</html>