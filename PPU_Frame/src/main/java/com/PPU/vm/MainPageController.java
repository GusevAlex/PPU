package com.PPU.vm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import com.PPU.DB.tables.PartnerCommercialMan;
import com.PPU.DB.tables.PartnersMZ;
import com.PPU.DB.tables.UsersComMan;
import com.PPU.DB.tables.UsersMunMan;
import com.PPU.DB.workLogic.WorkWithMZ;
import com.PPU.DB.workLogic.WorkWithUser;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import com.PPU.composite.Contact;
import com.PPU.composite.MenuItem;
import com.PPU.vo.AuthorBean;
import com.PPU.vo.ContactBean;
import com.PPU.vo.ContactGroupBean;
import com.PPU.vo.MenuGroupBean;
import com.PPU.vo.MenuItemBean;
import com.PPU.vo.PostBean;
import org.zkoss.web.fn.ServletFns;
import org.zkoss.web.servlet.Servlets;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelArray;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Popup;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;

public class MainPageController {
    @Wire
    private Popup feedbackPopup;

    private boolean mobile;

    private String orient = "landscape";

    private String css;
    private String nameUser;

    public String getCss() {
        return css;
    }

    public void setCss(String css) {
        this.css = css;
    }

    private AuthorBean currentUser;

    public AuthorBean getCurrentUser() {
        return this.currentUser;
    }

    // -------------------------------------------------------

    private MenuGroupBean[] menuGroups;

    public MainPageController()
    {
        Session session = Sessions.getCurrent();
        String login = (String) session.getAttribute("login");

        if (login == null)
            //грузим страницу авторизации
            Executions.sendRedirect("/pages/authoriz/authoriz.zul");
    }



    public MenuGroupBean[] getMenuGroups() {
        return menuGroups;
    }

    public void setMenuGroups(MenuGroupBean[] menuGroups) {
        this.menuGroups = menuGroups;
    }

    public ListModel<String[]> getToolbarModel() {

        return 	new ListModelList<String[]>(
                new String[][] {
//                { "О ППУ ",   "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAANFJREFUeNpiZEhrYSADTAPis0A8lwkqADLlPx78CkmzHBD7AfFMIDZjgQoWEbDxHZTuBWIDIHYD4nwgvgAzgBNKswPxLxyG9CJZBKJTQAwmNEW4NHchaX4ExPUwCSYiAgwUPqVQ9msgdgbijUC8DiTAQkAzyGsVUPZ3IPYB4jtAbAzEKsQYIA31FgjHAfEpdAX4DGCGOt0WiG8C8RdsivAZ8BeI0wkFEBMDhYDqBggRoUcUWxj8hQbaWxIs/4Xsgj5coYwngEF6GBjJzM5wABBgALS1KzDd2YvyAAAAAElFTkSuQmCC" },


                        { "Мои Программы > ",   "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAANFJREFUeNpiZEhrYSADTAPis0A8lwkqADLlPx78CkmzHBD7AfFMIDZjgQoWEbDxHZTuBWIDIHYD4nwgvgAzgBNKswPxLxyG9CJZBKJTQAwmNEW4NHchaX4ExPUwCSYiAgwUPqVQ9msgdgbijUC8DiTAQkAzyGsVUPZ3IPYB4jtAbAzEKsQYIA31FgjHAfEpdAX4DGCGOt0WiG8C8RdsivAZ8BeI0wkFEBMDhYDqBggRoUcUWxj8hQbaWxIs/4Xsgj5coYwngEF6GBjJzM5wABBgALS1KzDd2YvyAAAAAElFTkSuQmCC" },
                        { "Программа 1 > ",    "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAOtJREFUeNpiZEhrYaAEMAHxGSD+TwK+CsRCMANYgNgYiL8D8TUiLFQBYi0glgZiCSB+ygKVuAPEJkQYsAWIvaHsFUD8lwlNgRwQM6OJceIxUAjZAJCz7gFxL5rYdhya2UCWIRvwAoj7gHgN1H8gMA2I7YE4EIsBIJfxIBvwF4jroX58CLXZDyrXiMsPLGgmFkGjaAFUbBZaDOA0AOTXCQRioBSIdbEZ8B1qaxoJCfA7sgEOQGxAguZH0HQDN+AUFIO8UUeEAcbQWAG5+i8LmmQsgYSDDu4gG/AUiOWhqZFYcBndBa+hmGgAEGAAw0kvuVLIffoAAAAASUVORK5CYII=" },
                        { "Проекты > ", "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAO5JREFUeNpi/P//PwMIMKa3MpAK/s+sZmDCIccPxL1AfA+kDoifAPFMIBZFV4jNAHsgvgrERUCsCBWTBuI0qLgTPgNANqyAagCBLJDvgLgASX41EEvgMqAFSfIvEE+HsqdD+SAgBMTtuAzwRmIzA3EOEHNCaWYkOVcYgwXNACE0/mQoZsClDt0FZ4mMwbO4DJhFpAELcBmwEojvENAMkl+My4BfQJxCwIACqDqcCekgEC/DoXkrFONNiSBQBsTv0MTeQRMWAzEGPMXilUQgfkSsASCwHojnQtlTgHgTNkUsBAIsF4jZgLgYlwKAAAMAbegrRQ3UKeAAAAAASUVORK5CYII=" },
//                { "Диаграмма Ганта", "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAO5JREFUeNpi/P//PwMIMKa3MpAK/s+sZmDCIccPxL1AfA+kDoifAPFMIBZFV4jNAHsgvgrERUCsCBWTBuI0qLgTPgNANqyAagCBLJDvgLgASX41EEvgMqAFSfIvEE+HsqdD+SAgBMTtuAzwRmIzA3EOEHNCaWYkOVcYgwXNACE0/mQoZsClDt0FZ4mMwbO4DJhFpAELcBmwEojvENAMkl+My4BfQJxCwIACqDqcCekgEC/DoXkrFONNiSBQBsTv0MTeQRMWAzEGPMXilUQgfkSsASCwHojnQtlTgHgTNkUsBAIsF4jZgLgYlwKAAAMAbegrRQ3UKeAAAAAASUVORK5CYII=" }

                }
        );
    }

    private ContactGroupBean[] contactGroups;

    public ContactGroupBean[] getContactGroups() {
        return contactGroups;
    }

    // -------------------------------------------------------

    private PostBean currentPost = null;

    public PostBean getCurrentPost() {
        return currentPost;
    }

    public void setCurrentPost(PostBean currentPost) {
        this.currentPost = currentPost;
    }

    private String currentComment = null;

    public String getCurrentComment() {
        return currentComment;
    }

    public void setCurrentComment(String currentComment) {
        this.currentComment = currentComment;
    }

    private boolean modalShow = false;

    public boolean isModalShow() {
        return modalShow;
    }

    public void setModalShow(boolean modalShow) {
        this.modalShow = modalShow;
    }

    private boolean menuOpen = false;

    public boolean isMenuOpen() {
        return menuOpen;
    }

    public void setMenuOpen(boolean menuOpen) {
        this.menuOpen = menuOpen;
    }

    private boolean contactOpen = true;
    private boolean hideContact = false;

    public boolean isContactOpen() {
        return contactOpen;
    }

    public void setContactOpen(boolean contactOpen) {
        this.contactOpen = contactOpen;
    }

    private boolean likeStatus = false;

    public boolean isLikeStatus() {
        return (currentPost == null) ? false : currentPost.getLikeList().contains(currentUser);
    }

    public void setLikeStatus(boolean likeStatus) {
        this.likeStatus = likeStatus;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    private ServletRequest request = ServletFns.getCurrentRequest();

    @Init
    public void init() {

        Session session = Sessions.getCurrent();
        String login = (String) session.getAttribute("login");
        String typeOrg = (String) session.getAttribute("type");
        nameUser = (String) session.getAttribute("nameUser");

        mobile = Servlets.getBrowser(request, "mobile") != null;

        if (mobile) {
            css = "css/tablet.css.dsp";
        } else {
            css = "css/desktop.css.dsp";
        }

        currentUser = new AuthorBean("ZK Team", "images/avatars/zk.jpg");


        MenuGroupBean munGroup = new MenuGroupBean(
                "Муниципальные задания",
                new ListModelArray<MenuItemBean>(
                        new MenuItemBean[] {
                                new MenuItemBean(
                                        "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAutJREFUeNq0VU1IG0EUdmOi6yYmIT9EKaU1JFg0hxIJVEQNJPbixdKUFjw0OQg59OClQqmHgBJKPbSn2KSC0LMX8ZCLgSLUetCDYq6VnkoJarL9cdfort+TbFmSVDYGH3wsM7PzvXnvfTOvpeWGjdnY2GiKYGRk5Mp1fZ0NrfiYAQfA0SEa9CkDInAIHOtlWa7+oXN6ejo2Pj4+19bWxl0nqvPz8/Lm5ubi7OzsHFOJwgTQyf8AXSsrK58dDsedZlJXLpeFsbGx+0RuTSaTL51O5710Ov16e3tbZhiGpciOjo6Os9nsR0EQvmNOrpzOGgwGJ71ebz/mxbW1teVSqbSv0+kkkNpAGnW73R69Xs/id5Yc2Px+/wtKBzZ+gYN1Iifs7e3llpaWMvjnh+pwphOYx+N5x/P871QqlcPcN0AC7N3d3YGenh6PIiJy0A6yViI8PT01UqoUBzAeKOVyub8KeygUkgwGwy9aRxrtq6ury/DHX7IxTCvmXOq6XqpIIVQWqsZyHSH8mzMajSaCaqFWpmpy9fjs7IzyaAqHwyeqPRxyz9F6sVgszcAODg7yFXlaE4nEzNDQ0KimCHp7e0cnJiaecRzHKxuQRnZgYOBR5V8d6nd3cHCQeGSkysSybEdNiiRJuoTaAY1dLteteDz+pp4Mab0TNjU19aremtYayIVC4SdJUNMVlmXGarU6IALDlREo46+w+fn5t5gqaLxfllgs9jwSiTzVFEFfX58PNZyE/Ioaby/r8/mC/5VptYqQYvPw8PCTRp8JzfcAN5Xf3d1dRwSHWoghaw4RPMRlc15VA0YZ5/P5/YWFhU+N1CAajbZD2pGafkDkoihSH7ArzwX0/iCTyXxoREUWi8VRnSJZSUkgEHhss9n6zWazncYg1iHcriZqIJEDEdUX8by24yW8Taj3pjRqyAb1FkFHrW1ra+s9Jk7UxW4GeE6EnZ2dReJWOpqlkvuOa/Tgej1ZqPTkYstN24UAAwCE6hRrSCrxTwAAAABJRU5ErkJggg==",
                                        "Мои муниципальные задания", 8, ""),
                                new MenuItemBean(
                                        "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAu5JREFUeNq0VVFoUlEY9k6didvUajUXJBujgh70NQhb9OSL4mYNWQ972cMGwh6rhw0yo4eBD/ZgCYPFGgzU2DTGHqLYQzFh1ghiMgeDpjTnnBs6U6f2/eMaardgagc+7j33nvP95//O95/D4/3nxiwvL9dFoNFo/h2AhQQ4B7QCglPw54EkEANSQKF6AJFJDAbDnYGBgWednZ3XTptBNBrd8nq9lunpaTe6B1wZKOfm5pY6Ojqu1ipTMpmMm0ymm4eHh+voFsv/NZEs7e3tPcVikVcrJBLJWbVafR1cl4Eu4DzQXJKIzzAMnwbW046OjhRut/t9Op3OQbbVycnJp5FIZIMyYOpZfQm5XK5VLpd3YR+vIBvT+Pi4nYxz4ph6V89yNJXzKJXKG3jIGhagmkcoFIrocRKgUCg0hJyDh2lYBiKRqJmLhwIUGhFALBYL/xYgjQLZb2lpkddKDmnye2hcUpNN436//0UerVaLrqysvIb/96q/k/qUwQGKwra2tvZRpVJpstnspd7eXiNcICxfSTAY/Lq5uRkiO5atPIN5qwiwPjIycrc8A8oKj2NmYWGBp9PpGLa0zwDdDofDhYLpLg0OBAJvnU6ne2Ji4iGfz29FEOa3TRimiA0WtbW1VUi8vb39eXR0VCegVObn5ymfDEGv1/9AqX9TKBTdtArcFy9tNpvDbrc/IKeQlFKp9EJ1hsSDeTtYwDH2NDQ1NfWI5BdwbEwqHo9/ymQyt5Hdk5mZmVcko9lsHmPvC7FWq701ODhoxSEnLU1KJBLx4eHh+3jdYu+GBPBTwGGtdCgUehOLxfwg/4L+vsfjybOTon19fU2Li4vhVCq1MzQ09Fwmk12kSbu7u3RUB4HvFS7i2Pljn8+3AfIPeN/DCVnhLvQp5QSkW7JYLPfC4fA6fUeAAB2qf5QyJtTk/f7+ftpoMeqnx2q1PoaTnLOzs+9IlooALperrgo2Go10qImBLMlbfaMJGnCIZlhwtl8CDAA8PQ/NwxQAIgAAAABJRU5ErkJggg==",
                                        "Сообщения",  0, ""),
                                new MenuItemBean(
                                        "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyJJREFUeNqcVl9IU2EU35xtc7P9Y6z1JBUISSvpLUQkAiEECXG0t6CB+mRpQY+NNinSnA/2MJtC6ktCU0ZRCVo4EElQwbUHna621FrTtTZtztz6fXIv3G73zusO/GDfvec7v9853/nOnTiXy4mIicViEY9JACWgAkqBY0AWSAO/KGSAHNfmYhG/ianAJ1tbW82VlZXX9Hr9KY1Go89kMrubm5tr4XD44+DgYN/i4uIn+G0Bf/4LwpMBWWhqa2svW63Wx0aj8QyfCkI2NTXlttvtT7Bco7I5lEBVX19f19LS0qdQKErph1AdXVpamkUWJ8rLyy9KJJIi+t38/Ly3ra3tNn5GmJlwEUgNBoPJ5XK9JeWgH0aj0TUQ3ojH48tYKpqbm60Wi+UuU9XExMRTh8PxAD9/0GdSxJG1Bkra1Wq1npDTmJubG0fwAN6HgSAEvEgmkymmT1VV1U2TyXSWagQRFwFJQ1dRUVHH3EigUqkMeCenuqpYq9WqZDKZnOkjlUpLGhoaLFS3cRJIqqurTyuVSjWbAF10xWw2X4fPeZ1Od8lmszlwBsVsPzTEBVJCvjaVlJWVGelzYRrUyZuamh42NjbegYBSWj3b5HK5lhmXTbC/vr4e5dpIG0pzcPB8Pul0OsHsInaJ9icnJ5djsdhGNpsVFYJgMOhDnBQfAZEVw818xa6tEOzs7KRGRkZeIsY2HwGx5PDw8ABaMHlU9bhsnkgkspqvRMT24BScnp4eOIp63PINp9NJxkWcOfi4CMjLnz09PS7c3i9CgkN9bmxszJ5KpT4TgcxgRTzNQlL86vV6O/ZhhxH4/f53Ho/nNfNwDyMgtg1VbxYWFrz56p5IJLb6+/s74P+d+k4IJiDO0a6uLhtKFeJSTrIbHR29v7Ky4mePaSEEB+MedV0dGhq6hwuUZhPMzMw8R2k81FctVwjBQal8Pt8HjGInM3goFJrt7Ox8RI3mLN9mIQREWdztdj8LBALjJDjG9rfu7u5b1MdlL/9uSpEAk2HInevt7X1fU1NzFevj1HjPa2IB/yr+GZZACbAL/Oare6EZFGR/BRgALZ+XGZ2aoKsAAAAASUVORK5CYII=",
                                        "Создать муниципальное задание",	 5, ""),
                                new MenuItemBean(
                                        "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAA2VJREFUeNqsVl9IU2EUv1v7v8mcZbOxESgS+GI+mCDknxmob66nEgyCBCUfDNZT7EVGxFxIMOxBEtxLOoimswcHimn0MrAymbkedLiMmbatzbV/bp0j3x13624l88Dhu9/3ne/3u+d855x7OSsrKxRTWlpaBDBUgMpAI6A/wSZBsQibLWiOLTfvAAeG8+Pj48+Xl5e/4Ihzsk79jy1oji2HkEjIW1AajabWarW+5XK5nHQ6nenr62vz+Xwe2OLlcaSK2FLEoygeKuvv77/Z2Nh4h8fjpSYnJ+144MQ9GOvq6q6NjIwY4vG4iIkuFApjhWxTqRTP5XJZJyYmXiPBhZ6enqdSqVSBhmKx2JUA4fP5gmQymZBIJOrq6uobbHfAYquhbdVqdQMQrJ7DqIAHD+hDAoGAPzs76wBCzszMzJvW1tbrVSBsBOCF0G63Z207OjraLoLgHpCKpqamXqB7V5eWlj4wD+7s7Hj9fv93lUqlhjirqSKyu7vr29vb87HZarXahpOLy2QyOYcuE2Hbyxc1kUK2WYJoNBqLRCJBDoeTpkoQwOLKZLJyuA9RDoHH4/mk1+sfwtRPlSZKs9k8Wl9f35QlgBymIEXj8Li9sLDgOw1aZ2dn/lIUsRAzxwM6fsw4dnV18WFQMFpBilSrlJgcg4ZAf4D+ZoQpi1OQoLu7W4h3aDAYHjU3N99dW1t7Nj09/cpkMuU0r0Ag8KW3t1d7agKscIvFMgqFo8O1o6OjsmAwmIQUdobD4XK5XI5pqdrf398mnlBsBFz6DugFfM7Gj8ejHA6Hjd7HnB8cHLw3NjZmBgLx1tbW++HhYT0JUw4BjcFl84A8hwcGBu57vd5VMs+QMCSGhoZuQRrK5+fnLViXoPGiHrARzM3N4aGASCSKMvbRvqKmpkZ7cHDwbXFx8SMz9qcioN8auyY+Q29Bwkx7e/slaHLloVDoKwlNphjBX60ir9yPDw8PP29sbDyBfvMO5rHKysrA+vq6Ce4D+9evIhX97zpAAqfT6QF9TGogYbPZ3KBGkjmxQuBMggxO4COBRVWl0+lK7BRUFWLRIUaCOCwklUrlFaPRaIHUTJaCjuCAVYuYiI0Egc3NTRt87m4rFIom6gwE3j7tdrtfIjZ+cASk3+Dvh5A6G4mTX5jAHwEGAEF2JdGXXHDPAAAAAElFTkSuQmCC",
                                        "События",	 0, ""),
                        }
                )
        );

        MenuGroupBean commercGroup = new MenuGroupBean(
                "Проекты",
                new ListModelArray<MenuItemBean>(
                        new MenuItemBean[] {
                                new MenuItemBean(
                                        "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAutJREFUeNq0VU1IG0EUdmOi6yYmIT9EKaU1JFg0hxIJVEQNJPbixdKUFjw0OQg59OClQqmHgBJKPbSn2KSC0LMX8ZCLgSLUetCDYq6VnkoJarL9cdfort+TbFmSVDYGH3wsM7PzvXnvfTOvpeWGjdnY2GiKYGRk5Mp1fZ0NrfiYAQfA0SEa9CkDInAIHOtlWa7+oXN6ejo2Pj4+19bWxl0nqvPz8/Lm5ubi7OzsHFOJwgTQyf8AXSsrK58dDsedZlJXLpeFsbGx+0RuTSaTL51O5710Ov16e3tbZhiGpciOjo6Os9nsR0EQvmNOrpzOGgwGJ71ebz/mxbW1teVSqbSv0+kkkNpAGnW73R69Xs/id5Yc2Px+/wtKBzZ+gYN1Iifs7e3llpaWMvjnh+pwphOYx+N5x/P871QqlcPcN0AC7N3d3YGenh6PIiJy0A6yViI8PT01UqoUBzAeKOVyub8KeygUkgwGwy9aRxrtq6ury/DHX7IxTCvmXOq6XqpIIVQWqsZyHSH8mzMajSaCaqFWpmpy9fjs7IzyaAqHwyeqPRxyz9F6sVgszcAODg7yFXlaE4nEzNDQ0KimCHp7e0cnJiaecRzHKxuQRnZgYOBR5V8d6nd3cHCQeGSkysSybEdNiiRJuoTaAY1dLteteDz+pp4Mab0TNjU19aremtYayIVC4SdJUNMVlmXGarU6IALDlREo46+w+fn5t5gqaLxfllgs9jwSiTzVFEFfX58PNZyE/Ioaby/r8/mC/5VptYqQYvPw8PCTRp8JzfcAN5Xf3d1dRwSHWoghaw4RPMRlc15VA0YZ5/P5/YWFhU+N1CAajbZD2pGafkDkoihSH7ArzwX0/iCTyXxoREUWi8VRnSJZSUkgEHhss9n6zWazncYg1iHcriZqIJEDEdUX8by24yW8Taj3pjRqyAb1FkFHrW1ra+s9Jk7UxW4GeE6EnZ2dReJWOpqlkvuOa/Tgej1ZqPTkYstN24UAAwCE6hRrSCrxTwAAAABJRU5ErkJggg==",
                                        "Мои проекты", 8, ""),
                                new MenuItemBean(
                                        "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAu5JREFUeNq0VVFoUlEY9k6didvUajUXJBujgh70NQhb9OSL4mYNWQ972cMGwh6rhw0yo4eBD/ZgCYPFGgzU2DTGHqLYQzFh1ghiMgeDpjTnnBs6U6f2/eMaardgagc+7j33nvP95//O95/D4/3nxiwvL9dFoNFo/h2AhQQ4B7QCglPw54EkEANSQKF6AJFJDAbDnYGBgWednZ3XTptBNBrd8nq9lunpaTe6B1wZKOfm5pY6Ojqu1ipTMpmMm0ymm4eHh+voFsv/NZEs7e3tPcVikVcrJBLJWbVafR1cl4Eu4DzQXJKIzzAMnwbW046OjhRut/t9Op3OQbbVycnJp5FIZIMyYOpZfQm5XK5VLpd3YR+vIBvT+Pi4nYxz4ph6V89yNJXzKJXKG3jIGhagmkcoFIrocRKgUCg0hJyDh2lYBiKRqJmLhwIUGhFALBYL/xYgjQLZb2lpkddKDmnye2hcUpNN436//0UerVaLrqysvIb/96q/k/qUwQGKwra2tvZRpVJpstnspd7eXiNcICxfSTAY/Lq5uRkiO5atPIN5qwiwPjIycrc8A8oKj2NmYWGBp9PpGLa0zwDdDofDhYLpLg0OBAJvnU6ne2Ji4iGfz29FEOa3TRimiA0WtbW1VUi8vb39eXR0VCegVObn5ymfDEGv1/9AqX9TKBTdtArcFy9tNpvDbrc/IKeQlFKp9EJ1hsSDeTtYwDH2NDQ1NfWI5BdwbEwqHo9/ymQyt5Hdk5mZmVcko9lsHmPvC7FWq701ODhoxSEnLU1KJBLx4eHh+3jdYu+GBPBTwGGtdCgUehOLxfwg/4L+vsfjybOTon19fU2Li4vhVCq1MzQ09Fwmk12kSbu7u3RUB4HvFS7i2Pljn8+3AfIPeN/DCVnhLvQp5QSkW7JYLPfC4fA6fUeAAB2qf5QyJtTk/f7+ftpoMeqnx2q1PoaTnLOzs+9IlooALperrgo2Go10qImBLMlbfaMJGnCIZlhwtl8CDAA8PQ/NwxQAIgAAAABJRU5ErkJggg==",
                                        "Сообщения",  0, ""),
                                new MenuItemBean(
                                        "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyJJREFUeNqcVl9IU2EU35xtc7P9Y6z1JBUISSvpLUQkAiEECXG0t6CB+mRpQY+NNinSnA/2MJtC6ktCU0ZRCVo4EElQwbUHna621FrTtTZtztz6fXIv3G73zusO/GDfvec7v9853/nOnTiXy4mIicViEY9JACWgAkqBY0AWSAO/KGSAHNfmYhG/ianAJ1tbW82VlZXX9Hr9KY1Go89kMrubm5tr4XD44+DgYN/i4uIn+G0Bf/4LwpMBWWhqa2svW63Wx0aj8QyfCkI2NTXlttvtT7Bco7I5lEBVX19f19LS0qdQKErph1AdXVpamkUWJ8rLyy9KJJIi+t38/Ly3ra3tNn5GmJlwEUgNBoPJ5XK9JeWgH0aj0TUQ3ojH48tYKpqbm60Wi+UuU9XExMRTh8PxAD9/0GdSxJG1Bkra1Wq1npDTmJubG0fwAN6HgSAEvEgmkymmT1VV1U2TyXSWagQRFwFJQ1dRUVHH3EigUqkMeCenuqpYq9WqZDKZnOkjlUpLGhoaLFS3cRJIqqurTyuVSjWbAF10xWw2X4fPeZ1Od8lmszlwBsVsPzTEBVJCvjaVlJWVGelzYRrUyZuamh42NjbegYBSWj3b5HK5lhmXTbC/vr4e5dpIG0pzcPB8Pul0OsHsInaJ9icnJ5djsdhGNpsVFYJgMOhDnBQfAZEVw818xa6tEOzs7KRGRkZeIsY2HwGx5PDw8ABaMHlU9bhsnkgkspqvRMT24BScnp4eOIp63PINp9NJxkWcOfi4CMjLnz09PS7c3i9CgkN9bmxszJ5KpT4TgcxgRTzNQlL86vV6O/ZhhxH4/f53Ho/nNfNwDyMgtg1VbxYWFrz56p5IJLb6+/s74P+d+k4IJiDO0a6uLhtKFeJSTrIbHR29v7Ky4mePaSEEB+MedV0dGhq6hwuUZhPMzMw8R2k81FctVwjBQal8Pt8HjGInM3goFJrt7Ox8RI3mLN9mIQREWdztdj8LBALjJDjG9rfu7u5b1MdlL/9uSpEAk2HInevt7X1fU1NzFevj1HjPa2IB/yr+GZZACbAL/Oare6EZFGR/BRgALZ+XGZ2aoKsAAAAASUVORK5CYII=",
                                        "Создать проект",	 5, ""),
                                new MenuItemBean(
                                        "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAA2VJREFUeNqsVl9IU2EUv1v7v8mcZbOxESgS+GI+mCDknxmob66nEgyCBCUfDNZT7EVGxFxIMOxBEtxLOoimswcHimn0MrAymbkedLiMmbatzbV/bp0j3x13624l88Dhu9/3ne/3u+d855x7OSsrKxRTWlpaBDBUgMpAI6A/wSZBsQibLWiOLTfvAAeG8+Pj48+Xl5e/4Ihzsk79jy1oji2HkEjIW1AajabWarW+5XK5nHQ6nenr62vz+Xwe2OLlcaSK2FLEoygeKuvv77/Z2Nh4h8fjpSYnJ+144MQ9GOvq6q6NjIwY4vG4iIkuFApjhWxTqRTP5XJZJyYmXiPBhZ6enqdSqVSBhmKx2JUA4fP5gmQymZBIJOrq6uobbHfAYquhbdVqdQMQrJ7DqIAHD+hDAoGAPzs76wBCzszMzJvW1tbrVSBsBOCF0G63Z207OjraLoLgHpCKpqamXqB7V5eWlj4wD+7s7Hj9fv93lUqlhjirqSKyu7vr29vb87HZarXahpOLy2QyOYcuE2Hbyxc1kUK2WYJoNBqLRCJBDoeTpkoQwOLKZLJyuA9RDoHH4/mk1+sfwtRPlSZKs9k8Wl9f35QlgBymIEXj8Li9sLDgOw1aZ2dn/lIUsRAzxwM6fsw4dnV18WFQMFpBilSrlJgcg4ZAf4D+ZoQpi1OQoLu7W4h3aDAYHjU3N99dW1t7Nj09/cpkMuU0r0Ag8KW3t1d7agKscIvFMgqFo8O1o6OjsmAwmIQUdobD4XK5XI5pqdrf398mnlBsBFz6DugFfM7Gj8ejHA6Hjd7HnB8cHLw3NjZmBgLx1tbW++HhYT0JUw4BjcFl84A8hwcGBu57vd5VMs+QMCSGhoZuQRrK5+fnLViXoPGiHrARzM3N4aGASCSKMvbRvqKmpkZ7cHDwbXFx8SMz9qcioN8auyY+Q29Bwkx7e/slaHLloVDoKwlNphjBX60ir9yPDw8PP29sbDyBfvMO5rHKysrA+vq6Ce4D+9evIhX97zpAAqfT6QF9TGogYbPZ3KBGkjmxQuBMggxO4COBRVWl0+lK7BRUFWLRIUaCOCwklUrlFaPRaIHUTJaCjuCAVYuYiI0Egc3NTRt87m4rFIom6gwE3j7tdrtfIjZ+cASk3+Dvh5A6G4mTX5jAHwEGAEF2JdGXXHDPAAAAAElFTkSuQmCC",
                                        "События",	 0, ""),
                        }
                )
        );

        menuGroups = new MenuGroupBean[] {
                new MenuGroupBean(),

                new MenuGroupBean(
                        "Программы",
                        new ListModelArray<MenuItemBean>(
                                new MenuItemBean[] {
                                        new MenuItemBean(
                                                "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAddJREFUeNq0lt9HQ2EYx8/ajIhxGBEjYolSoqvmXI1RdrPuuolSN1E3XaTrka66SBcR6R/I0t252ayr6eIwxhhjjIgYY4y2vi/f5d3r3Wm/zsPHOWfv6/n9Pu983W7X8FIC+Xx+IgWxWMx1fcbwWALK9y04A9dgm7gKMrCLxxtogA1EVO0zoNTgFcyCAvgGpSGcrIEHGvhSF33ZbLb3vgpOp5SZO8uyimoEO+BY2dhiJCF6OgeaIEKPFwYYEJEUdSkyNAaEIj8VB/kUxiouBvy6GrQ0G00iZF5ZW3FxrPFnoNPp9N5Fod6nVIOSLoIkeGYH5cDVGIqF5/vgw7btcjweX5YjEEV5Ym4rjGhUaUltW5MjEL1/AB7BIVgaQ/kL2BK1SiQSJ2oNNsEFcDSt2mTK6mzTRSq7UfaJE7xHI/agk9zQeBaU2i5EI8GhZxEjEJ6npfwNkjqJaPYVaDjXNyoymUxvyDkcbusTtOcRdZ0nk0lHTpGYoJcgynrIHodBG3xKh61J1GYQ6bNAgg73HTRDE3KZJ7nKOpisg8N03v9bA5dZFKVHJiftLIeYyTS2R7lwivKAkjompPwWHkLnj25UrPE7PWpVU6mU6y3p+Z3s8/pvy68AAwD4ZZoWZfZ1ZwAAAABJRU5ErkJggg==",
                                                "Мои программы", 0, "/pages/include/pages/reviewMyProgram.zul"),
                                        new MenuItemBean(
                                                "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAu5JREFUeNq0VVFoUlEY9k6didvUajUXJBujgh70NQhb9OSL4mYNWQ972cMGwh6rhw0yo4eBD/ZgCYPFGgzU2DTGHqLYQzFh1ghiMgeDpjTnnBs6U6f2/eMaardgagc+7j33nvP95//O95/D4/3nxiwvL9dFoNFo/h2AhQQ4B7QCglPw54EkEANSQKF6AJFJDAbDnYGBgWednZ3XTptBNBrd8nq9lunpaTe6B1wZKOfm5pY6Ojqu1ipTMpmMm0ymm4eHh+voFsv/NZEs7e3tPcVikVcrJBLJWbVafR1cl4Eu4DzQXJKIzzAMnwbW046OjhRut/t9Op3OQbbVycnJp5FIZIMyYOpZfQm5XK5VLpd3YR+vIBvT+Pi4nYxz4ph6V89yNJXzKJXKG3jIGhagmkcoFIrocRKgUCg0hJyDh2lYBiKRqJmLhwIUGhFALBYL/xYgjQLZb2lpkddKDmnye2hcUpNN436//0UerVaLrqysvIb/96q/k/qUwQGKwra2tvZRpVJpstnspd7eXiNcICxfSTAY/Lq5uRkiO5atPIN5qwiwPjIycrc8A8oKj2NmYWGBp9PpGLa0zwDdDofDhYLpLg0OBAJvnU6ne2Ji4iGfz29FEOa3TRimiA0WtbW1VUi8vb39eXR0VCegVObn5ymfDEGv1/9AqX9TKBTdtArcFy9tNpvDbrc/IKeQlFKp9EJ1hsSDeTtYwDH2NDQ1NfWI5BdwbEwqHo9/ymQyt5Hdk5mZmVcko9lsHmPvC7FWq701ODhoxSEnLU1KJBLx4eHh+3jdYu+GBPBTwGGtdCgUehOLxfwg/4L+vsfjybOTon19fU2Li4vhVCq1MzQ09Fwmk12kSbu7u3RUB4HvFS7i2Pljn8+3AfIPeN/DCVnhLvQp5QSkW7JYLPfC4fA6fUeAAB2qf5QyJtTk/f7+ftpoMeqnx2q1PoaTnLOzs+9IlooALperrgo2Go10qImBLMlbfaMJGnCIZlhwtl8CDAA8PQ/NwxQAIgAAAABJRU5ErkJggg==",
                                                "Сообщения", 23, ""),

                                        new MenuItemBean(
                                                "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyJJREFUeNqcVl9IU2EU35xtc7P9Y6z1JBUISSvpLUQkAiEECXG0t6CB+mRpQY+NNinSnA/2MJtC6ktCU0ZRCVo4EElQwbUHna621FrTtTZtztz6fXIv3G73zusO/GDfvec7v9853/nOnTiXy4mIicViEY9JACWgAkqBY0AWSAO/KGSAHNfmYhG/ianAJ1tbW82VlZXX9Hr9KY1Go89kMrubm5tr4XD44+DgYN/i4uIn+G0Bf/4LwpMBWWhqa2svW63Wx0aj8QyfCkI2NTXlttvtT7Bco7I5lEBVX19f19LS0qdQKErph1AdXVpamkUWJ8rLyy9KJJIi+t38/Ly3ra3tNn5GmJlwEUgNBoPJ5XK9JeWgH0aj0TUQ3ojH48tYKpqbm60Wi+UuU9XExMRTh8PxAD9/0GdSxJG1Bkra1Wq1npDTmJubG0fwAN6HgSAEvEgmkymmT1VV1U2TyXSWagQRFwFJQ1dRUVHH3EigUqkMeCenuqpYq9WqZDKZnOkjlUpLGhoaLFS3cRJIqqurTyuVSjWbAF10xWw2X4fPeZ1Od8lmszlwBsVsPzTEBVJCvjaVlJWVGelzYRrUyZuamh42NjbegYBSWj3b5HK5lhmXTbC/vr4e5dpIG0pzcPB8Pul0OsHsInaJ9icnJ5djsdhGNpsVFYJgMOhDnBQfAZEVw818xa6tEOzs7KRGRkZeIsY2HwGx5PDw8ABaMHlU9bhsnkgkspqvRMT24BScnp4eOIp63PINp9NJxkWcOfi4CMjLnz09PS7c3i9CgkN9bmxszJ5KpT4TgcxgRTzNQlL86vV6O/ZhhxH4/f53Ho/nNfNwDyMgtg1VbxYWFrz56p5IJLb6+/s74P+d+k4IJiDO0a6uLhtKFeJSTrIbHR29v7Ky4mePaSEEB+MedV0dGhq6hwuUZhPMzMw8R2k81FctVwjBQal8Pt8HjGInM3goFJrt7Ox8RI3mLN9mIQREWdztdj8LBALjJDjG9rfu7u5b1MdlL/9uSpEAk2HInevt7X1fU1NzFevj1HjPa2IB/yr+GZZACbAL/Oare6EZFGR/BRgALZ+XGZ2aoKsAAAAASUVORK5CYII=",
                                                "Создать программу",	 5, ""),
                                }
                        )
                ),

                new MenuGroupBean(
                        " ",
                        new ListModelArray<MenuItemBean>(
                                new MenuItemBean[] {
                                        new MenuItemBean(
                                                "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAg5JREFUeNqklV9EQ1Ecx9cf0VOMMUYPvYyIEXuIu4f1MmpsJqWn3hJRIvaQHmI2MopI9NBTj6X00Mu4+0PGItLDXnoYY0/jEmNq6/tbv8txd++5p/rxca9zf7/vOef3+51zR0qlkuc/pmma9PuoS7wXbIE7UAOfTI3HtsrlslcmMFIsFu3GJ8EOSIMpl0UYIAdOI5FIR2UHfqCDrIK4h33IV0e6/UMT9Pt9j0AAVEHYMq4CxVSRkYDTBBPgBkz/QdyEYm90XZ8wJxinD2x7ICxJxRV4Ah+AVrkBZm38wqyVGxS5UCiYeXznrrFaFyyCimV8DJyAbZuYNpiJRqOGmaIl4HXY9hGogCB46//YAxgDu+DVJoa01sUarErySrWZB3kwKywoBL7As0NcbFCDXq9Hz6Ak94eMaHR4Xvh9ziFuXiyy/xe3wz1Y4drkTSEb81m7SMWo0GssnuVucbKuOEFL8dSegg4Lp118m2IN6i51MK3Fz6SCb13solvFk7oPLiQtLfJoneBDIegYbIKiix9pXYsTGCCjMEGQz4TPxS8Tj8cNaxdRAVdBSJLXS4Xcv7DW0GVH3bEMqnyZ/cWoc5YTiUTH6X/QBAuS4y+DYhYg3pT9cIgG0MAB18ZN2GBfLZlMNqxbcjrJtMUMOAd0K8Y4bSEhz7RSasXrVCrVdsrZuEtOKfCMsTWISwW+BRgAOqXRoo+2QCAAAAAASUVORK5CYII=",
                                                "Помощь",	  0, ""),
                                        new MenuItemBean(
                                                "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAi5JREFUeNqklVEoQ1EYx8e8rJSaiFarqZWIFBFZs4c1NS8rT0oRT1Ie1jx55EmeKE+iPClttfawtj3MEnlSUyKiRG7UsqyUsvl/+pbjuPe6s1O/7r3/853vnHvO952vJpPJmKppLpdLt7+2Al8d4B68gH6jg+pKpZJR2zVg4/d1MGBkkNofWMEoaJI0r/BNf2AXB2GrvcAmO6tJp9PiNw06ZYdvYAW0gAnWxJYH++ABzHM/aX1ut/taa4u6BEcWsKzz9w1gRkXrBZoTHIICqP9nUL2Dgx9nQBMI5MGKpFXCqsfjUfQmIGwq2jOYBm3ACWZZk+3sv6JIMmgBE5KmgG6wA8zMFmu3ku14KpVy/JigWCyawCA4AY/AylqZIFDAMrgCF2CdtUXJ1gJuEolEFri/wjQej9PzSYp7sTUDM3iU9HYO0VeNcTmfz9dYjqIPncgwa/R/cJ9Wy4lnMAIiGpHh5QNdEqMFXHOf2hjyNfa1RbFYTJyVtulCytpbMAQU7jfzO2X4ERAPlXLI6ff7Fa0oopVuSpoDZMEUqGdmWHNItruic63btKCyn7TybQOZ/PZXHlhAqIpMXohGoza9CXqBtYoJKAmH9bboTjhAurg2WJ9UyRMKwz22D/EF+cAX5nc9CIfDatcwFZRj4TysnIxi3HeC80AgYIpEIjSmB5zhO/fXIVPRSKqsNsmVjtolOS93wmlevqb/U5ODvEoqRHOGi34FxYRW3FppBfoUYACFWIRGhlH1RgAAAABJRU5ErkJggg==",
                                                "Настройки",		  0, ""),
                                }
                        )
                )
        };

        if (typeOrg != null && typeOrg.equals("Mun"))
            menuGroups[0] = munGroup;
        else
            menuGroups[0] = commercGroup;
        // -------------------------------------------------------------------------------

        // Contacts in the Contact Panel
//        contactGroups = new ContactGroupBean[] {
//                new ContactGroupBean(
//                        null,
//                        new ListModelArray<ContactBean>(
//                                new ContactBean[] {
//                                        new ContactBean("images/avatars/afro.png",		 "Alred",	"mobile"),
//                                        new ContactBean("images/avatars/alien.png",		 "Bruce",	"active"),
//                                        new ContactBean("images/avatars/anciano.png",	 "Selina",	""),
//                                        new ContactBean("images/avatars/artista.png",	 "Robin",	"active"),
//                                        new ContactBean("images/avatars/astronauta.png", "Harvey",  ""),
//                                        new ContactBean("images/avatars/barbaman.png",   "Tony", 	"mobile"),
//                                        new ContactBean("images/avatars/bombero.png",    "Nick",    "mobile"),
//                                        new ContactBean("images/avatars/boxeador.png",   "Peter",   "active"),
//                                        new ContactBean("images/avatars/bruce_lee.png",  "Clark",   "mobile"),
//                                }
//                        )
//                ),
//                new ContactGroupBean(
//                        "Other Online Contacts",
//                        new ListModelArray<ContactBean>(
//                                new ContactBean[] {
//                                        new ContactBean("images/avatars/caradebolsa.png", 	  "Gwen",	 "active"),
//                                        new ContactBean("images/avatars/chavo.png", 	  	  "Natasha", "active"),
//                                        new ContactBean("images/avatars/cientifica.png", 	  "Steve",	 "active"),
//                                        new ContactBean("images/avatars/cientifico_loco.png", "Mary",	 "active"),
//                                        new ContactBean("images/avatars/comisario.png",		  "Clint",	 "active"),
//                                        new ContactBean("images/avatars/cupido.png", 		  "Phil",	 "active"),
//                                        new ContactBean("images/avatars/diabla.png",		  "Pepper",  "active"),
//                                        new ContactBean("images/avatars/director.png", 		  "Curt",    "active"),
//                                        new ContactBean("images/avatars/dreds.png", 		  "Reed",    "active"),
//                                        new ContactBean("images/avatars/elsanto.png",		  "Sue", 	 "active"),
//                                        new ContactBean("images/avatars/elvis.png",			  "Johnny",  "active"),
//                                        new ContactBean("images/avatars/emo.png", 			  "Ben", 	 "active")
//                                }
//                        )
//                )
//        };
    }

    @AfterCompose
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        Double ie = Servlets.getBrowser(request, "ie");
        if (ie != null && ie < 8.0) {
            Clients.showNotification("This demo does not support IE6/7", true);
        }

        Selectors.wireComponents(view, this, false);
    }

    @Command
    @NotifyChange("modalShow")
    public void hideModal(){
        modalShow = false;
    }

    @Command
    @NotifyChange({"menuOpen", "contactOpen"})
    public void contentSwipe(@BindingParam("dir") String direction) {
        if ("up".equals(direction) || "down".equals(direction))
            return;

        menuOpen = "right".equals(direction);

        if ("landscape".equals(orient))
            contactOpen = !menuOpen;

        if (hideContact) contactOpen = false;
    }

    @Command
    @NotifyChange({"contactOpen", "menuOpen", "modalShow"})
    public void updateDeviceStatus(
            @BindingParam("orient") String orient,
            @BindingParam("width")  int    width) {

        Window mainWindow = ((Window) feedbackPopup.getFellow("mainWindow"));

        // Adjust width for desktop
        if (!mobile) {
            if (width > 1366) {
                mainWindow.setWidth("1366px");
                feedbackPopup.setWidth("700px");
            } else {
                mainWindow.setWidth("100%");
                feedbackPopup.setWidth("50%");
            }
        } else {
            feedbackPopup.setWidth("70%");
            feedbackPopup.setHeight("100%");

            // For mobile devices, responsive to orientation change
            if (!this.orient.equals(orient)) {
                this.orient = orient;

                // Android native browser does not resize after rotate
                Clients.resize(mainWindow);

                Clients.showNotification(orient, Clients.NOTIFICATION_TYPE_INFO, null, "middle_center", 2000);

                if ("portrait".equals(orient)) {
                    if (contactOpen) contactOpen = false;
                } else {
                    if (!menuOpen && !contactOpen) 	contactOpen = true;
                }

                feedbackPopup.close();
                modalShow = false;
            }
        }

        if (width <= 800) {
            hideContact = true;
            contactOpen = false;
        } else {
            hideContact = false;
            if (!menuOpen) contactOpen = true;

            if (!mobile) menuOpen = true;
        }
    }

    @Command
    public void showMessage(
            @ContextParam(ContextType.COMPONENT) Component comp,
            @BindingParam("pos")				 String	   pos
    ) {
        String msg = null;

        if (comp instanceof Listbox) {
            Listitem item = ((Listbox) comp).getSelectedItem();

            if (item instanceof Contact)
                msg = ((Contact) item).getName();
            else if (item instanceof MenuItem)
                msg = ((MenuItem) item).getTitle();

            ((Listbox) comp).setSelectedItem(null);
            comp = item;
        } else if (comp instanceof Toolbarbutton) {
            msg = ((Toolbarbutton) comp).getLabel();
        } else
            return;

        Clients.showNotification(msg, Clients.NOTIFICATION_TYPE_INFO, comp, pos, 2000);
    }

    @Command
    public void redirectToPage(
            @ContextParam(ContextType.COMPONENT) Component comp
    ) {
        String adress = new String();

        if (comp instanceof Listbox) {
            Listitem item = ((Listbox) comp).getSelectedItem();

            if (item instanceof MenuItem)
                adress = ((MenuItem) item).getAdressPage();

            ((Listbox) comp).setSelectedItem(null);
            comp = item;
        }
         else
            return;

        Executions.sendRedirect(adress);

    }

    public static Object getPartner()
    {
        Object partner = new Object();
        Object obj = new WorkWithUser().findAndGetAllRow("login", (String) Sessions.getCurrent().getAttribute("login"));

        if (((List)obj).size()!=0)
            if (((List) obj).get(0) instanceof UsersMunMan)
            {
                partner = ((UsersMunMan)((List) obj).get(0)).getPartnerMZ();
            }
            else
            if (((List) obj).get(0) instanceof UsersComMan)
            {
                partner = ((UsersComMan)((List) obj).get(0)).getPartnerProject();
            }

        return partner;
    }
}
